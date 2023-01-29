package demo.controller;

import java.util.Map;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class TestController {
	
	//@Autowired
	AssetService assetService=new AssetService();
	
	/*@RequestMapping(method=RequestMethod.GET, path="/getAssetData")
	@ResponseBody
	public Map<String,TreeSet<Map<String,Object>>> getAssetData(int id) {
		return assetService.getAssetData(id);	
	}
	
	
	@RequestMapping(method=RequestMethod.POST, path="/updateAsssetData")
	@ResponseBody
	public int updateAsssetData(Map<String,Object> assetMap) {
		return assetService.updateAsssetData(assetMap);	
	}

*/

}
