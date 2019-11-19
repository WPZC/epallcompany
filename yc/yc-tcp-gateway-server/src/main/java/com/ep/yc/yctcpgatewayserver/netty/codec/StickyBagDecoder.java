package com.ep.yc.yctcpgatewayserver.netty.codec;

import com.ep.yc.yctcpgatewayserver.netty.cache.CachePacket;
import com.ep.yc.yctcpgatewayserver.utils.HexByte;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 处理粘包解码器
 * @Author WQY
 * @Date 2019/11/11 14:57
 * @Version 1.0
 */
public class StickyBagDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

        byte[] req = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(req);
        String msg = HexByte.byte2HexStr(req);
        System.out.println("是否粘包验证："+msg);

        SplitBody(msg,list);

        //如果没有半包则拆分当前包
        //对消息头进行判断
        //因为半包数据很可能被清除所以要对当前包的数据进行简单验证
//        if(msg.substring(0,2).equals("7E")){
//
//            //7E也可能是半包的包未
//            if(!CachePacket.cacheStr.equals("")){
//                String packetMsgTop = CachePacket.cacheStr;
//
//                //获取整包字节数
//                int msgLength = Integer.parseInt(packetMsgTop.substring(10,14),16);
//
//                //获取下半包中的长度，验证下半包长度是否满足
//                int downLength = msg.length();
//                //判断缓存中的字节数是否等于上传上来的字节数
//                if(((msgLength*2)-packetMsgTop.length())==downLength){
//                    list.add(packetMsgTop+msg);
//                    CachePacket.cacheStr = "";
//                }
//            }else{
//                if(msg.length()>14){
//                    Split(msg,list);
//                }
//
//            }
//
//        }else{
//            //如果有半包则判断下一包数据是否是下半包
//            String packetMsgTop = CachePacket.cacheStr;
//            //获取整包字节数
//            int msgLength = Integer.parseInt(packetMsgTop.substring(10,14),16);
//            //获取下半包中的长度，验证下半包长度是否满足
//            int downLength = msg.length();
//            //判断缓存中的字节数是否等于上传上来的字节数
//            if(((msgLength*2)-packetMsgTop.length())==downLength){
//                list.add(packetMsgTop+msg);
//                CachePacket.cacheStr = "";
//            }else {
//                //如果小于等于则清空缓存和忽略该条数据
//                if(((msgLength*2)-packetMsgTop.length())>downLength){
//                    CachePacket.cacheStr = "";
//                }else{
//                    //如果大于，则说明此次上传的数据中含有下一个完整包或者半包
//                    //截取出当前缓存包的下半包，然后判断剩余字节中的开头是否是7E不是7E则舍弃
//                    int syl = (msgLength*2)-packetMsgTop.length();
//                    msg = msg.substring(0,syl);
//                    if(msg.substring(0,2).equals("7E")){
//                        Split(msg,list);
//                    }
//                }
//            }
//        }
    }

    public void Split(String msg,List<Object> list){
        //获取整包字节数
        int msgLength = Integer.parseInt(msg.substring(10,14),16);
        //查看当前包字节数是否等于当钱包字节数，如果等于则加入list进行下一步解析
        if (msgLength*2 == msg.length()){
            list.add(msg);
        }else if(msgLength*2 > msg.length()){//查看当前包字节数是否大于当钱包字节数，如果大于则存入粘包缓存区
            CachePacket.cacheStr = msg;
        }else if(msgLength*2 < msg.length()){//查看当前包字节数是否小于当钱包字节数，如果小于则拆分出整包将剩余半包加入缓存
            while (true){
                //判断msg的长度是否大于14，大于才能进行分解和拆包
                if(msg.length()>14){
                    if(msg.substring(0,2).equals("7E")){
                        int length = Integer.parseInt(msg.substring(10,14),16);
                        if(length*2 > msg.length()){
                            CachePacket.cacheStr = msg;
                            break;
                        }
                    }
                }else{
                    break;
                }
                String integrity = msg.substring(0,msgLength*2);
                //校验结尾是否为7E
                if(!integrity.substring(integrity.length()-2,integrity.length()).equals("7E")){
                    int index = msg.indexOf("7E")+2;
                    if(msg.substring(index,index+2).equals("7E")){
                        msg = msg.substring(index);
                    }else{
                        msg = msg.substring(index-2);
                    }
                    System.out.println(msg);
                }else{
                    msg = msg.substring(msgLength*2);
                    list.add(integrity);
                }


            }
        }
    }


    public void SplitBody(String msg,List<Object> list){


        //判断包头是否是7E，如果是7E则为正常包开头，正常解析
        if(msg.substring(0,2).equals("7E")){
            //首先判断整包消息是否满足字节数*2大于14（从包首到字节长度位）
            if(msg.length()<14){
                return;
            }
            //获取消息长度
            int msgLength = Integer.parseInt(msg.substring(10,14),16)*2;
            //判断该条消息是否等于msgLength
            if(msg.length()==msgLength){
                list.add(msg);
            }else{
                //如果该条消息未满足msg.length()==msgLength则表示为半包或者粘包
                if(msg.length()>msgLength){
                    //大于的话则表示粘包了
                    //先取出粘包之前的字节
                    String msgBody = msg.substring(0,msgLength);
                    list.add(msgBody);
                    SplitBody(msg.substring(msgLength),list);
                }else {
                    //小于的时候则是半包
                    //加入缓存
                    if(msg.length()>=14){
                        CachePacket.cacheStr = msg;
                    }

                }
            }
        }else{
            //消息头不为7E,则上传的是粘包数据
            //看缓存中是否有缓存数据
            if(CachePacket.cacheStr.equals("")){
                //如果缓存种数据为空则截取掉7E之前的数据
                int index = msg.indexOf("7E");
                if(index==-1){
                    return;
                }else{
                    SplitBody(msg.substring(index),list);
                }
            }else{
                //如果有缓存则对缓存中的长度进行截取
                String cache = CachePacket.cacheStr;
                //获取消息长度
                int msgLength = Integer.parseInt(cache.substring(10,14),16)*2;
                //验证该条数据是否含有7E
                int index = msg.indexOf("7E");
                if(index==-1){
                    //没有7E则表示该数据还是一个半包，或者恶意发送的数据则踢掉
                    //清空缓存
                    CachePacket.cacheStr = "";
                }else{
                    //没有！=-1则表示含有7E
                    //然后看该7Eindex的后两位是否还是7E如果是则表示有下个包需要截出第二个7E之前的数据进行长度校验
                    //截取紧挨着第二个7E之前的数据，进行长度校验
                    if((index+2)==msg.length()){
                        //如果等于的话则表示该7E就是最后一个字节了
                        String str = msg.substring(0,index+2);
                        if(str.length()==(msgLength-cache.length())){
                            list.add(cache+str);
                        }
                        CachePacket.cacheStr = "";

                    }else{
                        if(msg.substring(index,index+2).equals("7E")){
                            //下个字节还是7E则进行长度校验
                            String str = msg.substring(0,index+2);
                            if(str.length()==(msgLength-cache.length())){
                                list.add(cache+str);
                            }
                            CachePacket.cacheStr = "";
                            msg = msg.substring(index+2);
                            SplitBody(msg,list);
                        }else{
                            //如果第二个不是7E，则7E可能还在下个里面,忽略
                            String str = msg.substring(0,index+2);
                            if(str.length()==(msgLength-cache.length())){
                                list.add(cache+str);
                                //长度校验合格则忽略后面不带7E的数据
                            }
                            CachePacket.cacheStr = "";

                        }
                    }
                }
            }
        }
    }


}
