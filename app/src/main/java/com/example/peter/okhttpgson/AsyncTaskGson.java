package com.example.peter.okhttpgson;

import android.os.AsyncTask;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OkHttpでJSONを取得し、Gsonでパースする
 *
 * @author :ryo.yamada
 * @since :1.0 :2017/08/25
 */
class AsyncTaskGson extends AsyncTask<String, Void, String> {

    private final MainActivity mActivity;

    /**
     * コンストラクタ
     *
     * @param mActivity Activity
     */
    AsyncTaskGson(MainActivity mActivity) {
        this.mActivity = mActivity;
    }

    /**
     * 非同期で行う処理
     *
     * @param uri uri
     * @return 処理結果
     */
    @Override
    protected String doInBackground(String... uri) {
        String result = null;

        // リクエストオブジェクトの生成
        Request request = new Request.Builder()
                .url(uri[0])
                .get()
                .build();

        // クライアントオブジェクトの生成
        OkHttpClient client = new OkHttpClient();

        // リクエストして結果を受け取る
        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * doInBackgroundの後の処理
     *
     * @param result 非同期処理の結果
     */
    @Override
    protected void onPostExecute(String result) {
        // Gsonの生成
        Gson gson = new GsonBuilder().create();
        // デシリアライズ
        ResultApi resultApi = gson.fromJson(result, new TypeToken<ResultApi>() {
        }.getType());
        List<LargeArea> list = resultApi.getResults().getLargeAreas();

        GsonArrayAdapter adapter = new GsonArrayAdapter(mActivity, list);
        ListView listView = mActivity.findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }
}
