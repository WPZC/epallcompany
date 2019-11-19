package com.ep.yc.ycview.service.impl;

import com.ep.yc.ycview.entity.OrganizationInput;
import com.ep.yc.ycview.entity.YcOrganizationEntity;
import com.ep.yc.ycview.entity.YcPlatformInfoEntity;
import com.ep.yc.ycview.entity.YcUserEntity;
import com.ep.yc.ycview.requestdb.SendDbBase;
import com.ep.yc.ycview.service.UserManagementService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author WQY
 * @date 2019/9/10 15:14
 */
@Service
public class UserManagementServiceImpl implements UserManagementService {

    @Autowired
    private SendDbBase sendDbBase;

    @Override
    public String addUser(YcUserEntity ycUserEntity) {

        //添加
        String rs = sendDbBase.addUser(ycUserEntity);
        //判断返回结果如果是SUCCESS则成功，否则返回提示信息
        if("SUCCESS".equals(rs)){
            return "SUCCESS";
        }else{
            return rs;
        }
    }

    @Override
    public List<YcUserEntity> getuserlist(String orgnum) {
        String json = sendDbBase.getuserlist(orgnum);
        List<YcUserEntity> ycUserEntities = new Gson().fromJson(json,new TypeToken<List<YcUserEntity>>(){}.getType());
        return ycUserEntities;
    }

    @Override
    public List<YcOrganizationEntity> getorglist(String orgnum) {

        String json = sendDbBase.findOrganizationBynum(orgnum+"0%");

        List<YcOrganizationEntity> ycOrganizationEntities = new Gson().fromJson(json,new TypeToken<List<YcOrganizationEntity>>(){}.getType());

        return ycOrganizationEntities;
    }

    @Override
    public String deleteUser(String username) {
        String rs = sendDbBase.deleteUser(username);
        return rs;
    }

    private List<HashMap<String,Object>> outRs = null;
    @Override
    public List<HashMap<String,Object>> findByOrganizationList(String orgNum) {

        List<YcOrganizationEntity> organizationEntities = new Gson().fromJson(sendDbBase.getOrganizationList(),new TypeToken<List<YcOrganizationEntity>>(){}.getType());
        LinkedHashMap<String,Object> map = getOrganizationListGson(organizationEntities).get("1000");
        rsList = new ArrayList<HashMap<String, Object>>();
        HashMap<String,Object> rs = new HashMap<String, Object>();
        outRs = new ArrayList<HashMap<String, Object>>();
        //调用递归
        dg2(map,orgNum);
        return outRs;
    }


    private HashMap<String,Object> dg2(LinkedHashMap<String,Object> map,String orgNum){

        //返回值
        HashMap<String,Object> rsout = new HashMap<String, Object>();
        //存放每一级的list
        List<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        //存放当前递归曾的id和label，最底层的时候只有这个，然后从倒数第二层开始往dg2map中添加children
        //并将上一级的list添加到children里
        HashMap<String,Object> dg2Map = new HashMap<String, Object>();
        //从最上级开始遍历
        for (String key:map.keySet()){
            if(key.contains("1000")){
                //调用递归，在最后一级开始往会返回数据
                HashMap<String,Object> dg2Map2 = dg2((LinkedHashMap<String,Object>)map.get(key),orgNum);
                //由于当前层可能会有多个兄弟，所以要判断children是否为空
                if(dg2Map.get("children")==null){
                    list.add((HashMap<String,String>)dg2Map2.get("dg2Map"));
                    dg2Map.put("children",list);
                }else{
                    //不为null则说明已经存在直接把儿子添加进来就行
                    ((List<HashMap<String,String>>)dg2Map.get("children")).add((HashMap<String,String>)dg2Map2.get("dg2Map"));
                }
            }else{
                //为每层添加标识
                dg2Map.put(key,(String) map.get(key));
            }
        }
        //接受orgNum，根据orgNum返回对应的组织机构
        if(dg2Map.get("id").equals(orgNum)){
            outRs.add(dg2Map);
        }
        System.out.println(new Gson().toJson(dg2Map));
        rsout.put("list",list);
        rsout.put("dg2Map",dg2Map);

        return rsout;
    }

    HashMap<String,String> map1 = null;
    HashMap<String,Object> rsMap = new HashMap<String, Object>();
    List<HashMap<String,Object>> rsList = new ArrayList<HashMap<String, Object>>();
    private List<HashMap<String,String>> dg(LinkedHashMap<String,Object> map){

        List<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        for (String key:map.keySet()){
            if((!key.equals("id")&&!key.equals("label"))||map.size()==2){
                //等于2为最底层
                if(map.size()==2){
                    if(map1==null){
                        map1 = new HashMap<String, String>();
                        map1.put(key,(String) map.get(key));
                        list.add(map1);
                    }else{
                        map1.put(key,(String) map.get(key));
                    }

                }else{
                    List<HashMap<String,String>> out = dg((LinkedHashMap<String,Object>)map.get(key));

                    //为null则已经到了最顶层
                    if(map.get("id")==null){
                        if(out.size()>0){
                            rsMap = new HashMap<String, Object>();
                            rsMap.put("id",out.get(0).get("id"));
                            rsMap.put("label",out.get(0).get("label"));
                            rsList.add(rsMap);
                        }

                    }else{
                        if(rsMap.size()==0){
                            rsMap = new HashMap<String, Object>();
                            rsMap.put("id",map.get("id"));
                            rsMap.put("label",map.get("label"));
                            rsMap.put("children",out);
                            rsList.add(rsMap);
                        }else{

                            if(rsMap.get("label").equals(map.get("label"))){
                                ((List<HashMap<String,String>>)rsList.get(rsList.size()-1).get("children")).add(out.get(0));
                            }else{
                                rsMap = new HashMap<String, Object>();
                                rsMap.put("id",map.get("id"));
                                rsMap.put("label",map.get("label"));
                                rsMap.put("children",out);
                                rsList.add(rsMap);
                            }
                        }


                    }
                    System.out.println(list);

                }
            }
        }
        map1 = null;
        return list;
    }


    private LinkedHashMap<String,LinkedHashMap<String,Object>> getOrganizationListGson(List<YcOrganizationEntity> organizationEntities){

        //查看最大梯度等级
        int maxTd = 0;
        int serialnum = 4;
        for (int i = 0,num=organizationEntities.size();i<num;i++){
            if(organizationEntities.get(i).getOrganizationSerialNum().length()>serialnum){
                serialnum = organizationEntities.get(i).getOrganizationSerialNum().length();
            }
        }

        //分出梯度
        maxTd = serialnum/4;
        TreeMap<Integer,List<YcOrganizationEntity>> map = new TreeMap<Integer, List<YcOrganizationEntity>>();
        for (int i = 1;i<=maxTd;i++){
            List<YcOrganizationEntity> listtd = new ArrayList<YcOrganizationEntity>();
            for (int y = 0,num=organizationEntities.size();y<num;y++){
                if(organizationEntities.get(y).getOrganizationSerialNum().length()==i*4){
                    listtd.add(organizationEntities.get(y));
                }
            }
            map.put(i,listtd);
        }
        //对梯度进行链表关联，分出主干和树杈
        //利用对象指针和堆栈特性
        List<YcOrganizationEntity> entityList = null;
        List<LinkedHashMap<String,Object>> list = new ArrayList<LinkedHashMap<String, Object>>();
        LinkedHashMap<String,Object> linkedHashMap = new LinkedHashMap<String, Object>();
        LinkedHashMap<String,LinkedHashMap<String,Object>> treemap = new LinkedHashMap<String, LinkedHashMap<String,Object>>();
        for (Integer key:map.keySet()){
            entityList = map.get(key);
            for (int i = 0;i<entityList.size();i++){
                String serialNum = entityList.get(i).getOrganizationSerialNum();
                String ornName = entityList.get(i).getOrganizationName();
                if(linkedHashMap.size()==0){
                    linkedHashMap.put("id",serialNum);
                    linkedHashMap.put("label",ornName);
                    treemap.put(serialNum,linkedHashMap);
                }else{
                    linkedHashMap = new LinkedHashMap<String, Object>();
                    linkedHashMap.put("id",serialNum);
                    linkedHashMap.put("label",ornName);
                    System.out.println(serialNum.substring(0,serialNum.length()-4));

                    if(treemap.get(serialNum.substring(0,serialNum.length()-4))!=null){

                        treemap.get(serialNum.substring(0,serialNum.length()-4)).
                                put(serialNum,linkedHashMap);
                        treemap.put(serialNum,linkedHashMap);

                    }else{
                        treemap.put(serialNum,linkedHashMap);
                    }

                }
            }
        }
        return treemap;
    }




    @Override
    public String addOrganization(OrganizationInput organizationInput) throws Exception{

        List<YcOrganizationEntity> organizationEntities = new Gson().fromJson(sendDbBase.getOrganizationList(),new TypeToken<List<YcOrganizationEntity>>(){}.getType());

        //判断组织机构名称是否存在
        for (int i = 0,num = organizationEntities.size();i<num;i++){
            if (organizationEntities.get(i).getOrganizationName().equals(organizationInput.getOrganization_name())){
                return "组织机构名称已存在";
            }
        }

        //获取住址机构分级
        LinkedHashMap<String,LinkedHashMap<String,Object>> linkedHashMap = getOrganizationListGson(organizationEntities);

        //获取到本账号权限下的所有设备
        LinkedHashMap<String,Object> rsNum = linkedHashMap.get(organizationInput.getUporganization_serial_num());

        int max = 0;
        int now = 0;
        for (String key:rsNum.keySet()){
            if(key.contains("1000")){
                if(max<Integer.parseInt(key.substring(key.length()-4,key.length()))){
                    max = Integer.parseInt(key.substring(key.length()-4,key.length()));
                }
            }
        }
        max++;
        String serinum = max+"";
        for (int i = 0,num = serinum.length();i<4-num;i++){
            serinum ="0" + serinum ;
        }

        YcOrganizationEntity ycOrganizationEntity = new YcOrganizationEntity();
        ycOrganizationEntity.setOrganizationName(organizationInput.getOrganization_name());
        ycOrganizationEntity.setOrganizationSerialNum(organizationInput.getUporganization_serial_num()+serinum);
        ycOrganizationEntity.setContactPersonName(organizationInput.getContactPersonName());
        ycOrganizationEntity.setContactPersonPhone(organizationInput.getContactPersonPhone());
        ycOrganizationEntity.setSupervisionType(organizationInput.getSupervisionType());
        return sendDbBase.addOrganization(ycOrganizationEntity);

    }

    @Override
    public String deleteOrg(String orgNum) throws Exception {
        String rs = sendDbBase.deleteOrgNum(orgNum);
        return rs;
    }

    @Override
    public String addtoplatform(YcPlatformInfoEntity ycPlatformInfoEntity) throws Exception {
        String rs = sendDbBase.addtoplatform(ycPlatformInfoEntity);
        return rs;
    }

    @Override
    public String deletetoplatform(String platform) throws Exception {
        String rs = sendDbBase.deletetoplatform(platform);
        return rs;
    }

    @Override
    public List<YcPlatformInfoEntity> gettoplatformlist() throws Exception {

        String rs = sendDbBase.gettoplatformlist();

        List<YcPlatformInfoEntity> ycPlatformInfoEntities = new Gson().fromJson(rs,new TypeToken<List<YcPlatformInfoEntity>>(){}.getType());

        return ycPlatformInfoEntities;
    }
}
