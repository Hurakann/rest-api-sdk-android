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

public class MergeResource implements PutRequestExectue, getRequestExectue, PostRequestExectue {

	ResourceMerge merge_resource;
	private static String URI = "/user/merge";
	private static String CTYPE = "application/json;";
	private ClientPOSTAsync cPOSTAsync;
	private ClientPUTAsync cPUTAsync;
	private ClientGETAsync cGETAsync;

	public interface ResourceMerge {
		void onMerge(Response response);

		void onUpdateMerge(Response response);

		void onGetMerge(Response response);
	}

	public MergeResource(ResourceMerge rm) {
		merge_resource = rm;
	}

	public void mergeUsers(Merge merge) throws ClientProtocolException, URISyntaxException, IOException {
		cPOSTAsync = new ClientPOSTAsync(this);
		Gson gs = new Gson();
		cPOSTAsync.execute(URI, gs.toJson(merge), CTYPE);
	}

	public void getMergeByUser(String user_id) throws ClientProtocolException, URISyntaxException, IOException {
		cGETAsync = new ClientGETAsync(this);
		cGETAsync.execute(URI, "?user_id=" + user_id, CTYPE);
	}

	public void updateMerge(Merge merge) throws ClientProtocolException, URISyntaxException, IOException {
		cPUTAsync = new ClientPUTAsync(this);
		Gson gs = new Gson();
		cPUTAsync.execute(URI, gs.toJson(merge), CTYPE);
	}

	@Override
	public void doPutExecute(Response response) {
		merge_resource.onUpdateMerge(response);
	}

	@Override
	public void doPostExecute(Response response) {
		merge_resource.onMerge(response);
	}

	@Override
	public void doGetExecute(Response response) {
		merge_resource.onGetMerge(response);
	}

	public void cancelRequest() {
		if (cPOSTAsync != null) {
			cPOSTAsync.cancelRequest();
			cPOSTAsync.cancel(true);
		} else if (cPUTAsync != null) {
			cPUTAsync.cancelRequest();
			cPUTAsync.cancel(true);
		} else if (cGETAsync != null) {
			cGETAsync.cancelRequest();
			cGETAsync.cancel(true);
		} else {
		}
	}

}
