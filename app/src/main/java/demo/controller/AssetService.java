package demo.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.springframework.stereotype.Component;


//@Component
public class AssetService {
	
	List<Map<String,Object>> cache=new ArrayList<>();
	
	public Map<String, TreeSet<Map<String, Object>>> getAssetData(List<Map<String, Object>> assetListFromDAO) {
//		List<Map<String, Object>> assetListFromDAO=cache;//new ArrayList<>();// get map from db
		Map<String, TreeSet<Map<String, Object>>> resultMap=new HashMap<String, TreeSet<Map<String,Object>>>();
		for (Map<String, Object> map : assetListFromDAO) {
			String assetName=(String)map.get("asset");
			if(resultMap.containsKey(assetName)) {
				TreeSet<Map<String, Object>> set=resultMap.get(assetName);
				map.remove("asset");
				set.add(map);
				resultMap.put(assetName, set);
			}else {
				TreeSet<Map<String, Object>> set = new TreeSet<Map<String,Object>>(new Comparator<Map<String,Object>>() {

					@Override
					public int compare(Map<String, Object> o1, Map<String, Object> o2) {
						int temp1=(Integer)o1.get("timestamp");
						int temp2=(Integer)o2.get("timestamp");
						return temp1-temp2;
					}
				});
				map.remove("asset");
				set.add(map);
				resultMap.put(assetName, set);
			}
			
		}
		return resultMap;
	}

	public int updateAsssetData(Map assetMap) {
		cache.add(assetMap);
		System.out.println(cache);
		return 0;
	}
	
	public static void main(String[] a) {
		List<Map<String,Object>> cache=new ArrayList<>();
		cache.add(getTestMap("asset1",923, 45,80));
		cache.add(getTestMap("asset3",930, 45,80));
		cache.add(getTestMap("asset1",915, 40,60));
		cache.add(getTestMap("asset1",933, 76,100));
		cache.add(getTestMap("asset2",943, 90,120));
		cache.add(getTestMap("asset2",923, 45,80));
		cache.add(getTestMap("asset3",943, 80,45));
		System.out.println(cache);
		System.out.println(new AssetService().getAssetData(cache));
	}

	private static Map<String, Object> getTestMap(String asset,int i, int j, int k) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("asset", asset);
		map.put("temperature", j);
		map.put("pressure", k);
		map.put("timestamp", i);
		return map;
	}

}
