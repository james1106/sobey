package com.magic.sangha.util;

import java.util.Collections;
import java.util.Comparator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *  ºØ∫œ≈≈–Ú
 * @author QimouXie
 *
 */
public class ListSortUtil {

	@SuppressWarnings("unchecked")
	public static JSONArray jsonArraySort(JSONArray array){
		
		Collections.sort(array, new Comparator<Object>() {

			@Override
			public int compare(Object o1, Object o2) {
				JSONObject o1Obj = (JSONObject)o1;
				JSONObject o2Obj = (JSONObject)o2;
				long o1Time = o1Obj.getLong("createtime");
				long o2Time = o2Obj.getLong("createtime");
				if(o1Time > o2Time){
					return -1;
				}else if(o1Time < o2Time){
					return 1;
				}
				return 0;
			}
		});
		
		return array;
	}
	
}
