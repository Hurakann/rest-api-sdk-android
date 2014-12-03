package org.hova.hover.sdk.merge;

import java.io.IOException;
import java.net.URISyntaxException;
import org.apache.http.client.ClientProtocolException;
import org.hova.hover.sdk.http.ClientGETAsync.getRequestExectue;
import org.hova.hover.sdk.http.ClientPOSTAsync.PostRequestExectue;
import org.hova.hover.sdk.http.ClientPUTAsync.PutRequestExectue;
import org.hova.hover.sdk.http.ClientGETAsync;
import org.hova.hover.sdk.http.ClientPOSTAsync;
import org.hova.hover.sdk.http.ClientPUTAsync;
import org.hova.hover.sdk.http.Response;
import org.hova.hover.sdk.pojo.Merge;

import com.google.gson.Gson;


public class MergeResource implements PutRequestExectue, getRequestExectue, PostRequestExectue{

	ResourceMerge merge_resource;
	private static String URI = "/user/merge";
	private static String CTYPE= "application/json;";
	
	
	public interface ResourceMerge{
		void onMerge(Response result);
		void onUpdateMerge(Response result);
		void onGetMerge(Response result);
	}
	
	public MergeResource( ResourceMerge rm){
		merge_resource=rm;
	}
	
	public void mergeUsers(Merge merge) throws ClientProtocolException, URISyntaxException, IOException{
        ClientPOSTAsync req=new ClientPOSTAsync(this);
        Gson gs=new Gson();
        req.execute(URI,gs.toJson(merge),CTYPE);
	}
	
	public void getMergeByUser(String user_id) throws ClientProtocolException, URISyntaxException, IOException{
        ClientGETAsync req= new ClientGETAsync(this);
        req.execute(URI,"?user_id="+user_id,CTYPE);
	}
	
	public void updateMerge(Merge merge) throws ClientProtocolException, URISyntaxException, IOException{
        ClientPUTAsync req=new ClientPUTAsync(this);
        Gson gs=new Gson();
        req.execute(URI,gs.toJson(merge),CTYPE);
	}
	
	@Override
	public void doPutExecute(Response result) {
		merge_resource.onUpdateMerge(result);
	}

	@Override
	public void doPostExecute(Response result) {
		merge_resource.onMerge(result);
	}

	@Override
	public void doGetExecute(Response result) {
		merge_resource.onGetMerge(result);
	}
	
}
