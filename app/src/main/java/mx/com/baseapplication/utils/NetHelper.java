package mx.com.baseapplication.utils;

import android.content.Context;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import mx.com.baseapplication.model.GenericalError;


public class NetHelper {


    public static String TAG = NetHelper.class.getSimpleName();

    private Context context;






    public interface OnDataResultInterface{
        void OnResultOk(Object object);
        void OnError(GenericalError error);
    }

    private OnDataResultInterface onDataResultInterface;

    public void setOnDataResultInterface(OnDataResultInterface onDataResultInterface) {
        this.onDataResultInterface = onDataResultInterface;
    }

    public NetHelper(Context context){
        this.context = context;
    }



    private void processResponse(Class clase,String response){
        Gson gson = new Gson();

        try{

            Object object = gson.fromJson(response,clase);
            if(object!=null){
                if(onDataResultInterface!=null){
                    onDataResultInterface.OnResultOk(object);
                }
            }else{
                if(onDataResultInterface!=null){
                    GenericalError genericalError = new GenericalError();
                    genericalError.setDescripcion("Empty response");
                    genericalError.setErrorType(Enums.ErrorType.WARNING);
                    onDataResultInterface.OnError(genericalError);
                }
            }


        }catch (Exception e){
            if(onDataResultInterface!=null){
                GenericalError genericalError = new GenericalError();
                genericalError.setDescripcion(e.getLocalizedMessage());
                genericalError.setErrorType(Enums.ErrorType.WARNING);
                onDataResultInterface.OnError(genericalError);
            }
        }
    }



    public void getRequestWithParams(final Class requestClass ,String method, final Map<String, String> params,final Map<String, String> headers){


        final RequestQueue mRequestQueue = Volley.newRequestQueue(context);

        StringBuilder urlBuilder = new StringBuilder(Constants.BASE_URL).append(method);
        if(params!=null && params.size()>0){
            urlBuilder.append("?");
            for(String key :params.keySet()){
                urlBuilder.append(key).append("=").append(params.get(key));
            }
        }



        String url = urlBuilder.toString();


        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new com.android.volley.Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        if(Constants.DEBUG){
                            Log.d(TAG,response);
                        }


                        processResponse(requestClass,response);
                        mRequestQueue.stop();



                    }
                },
                new com.android.volley.Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        mRequestQueue.stop();
                        GenericalError genericalError = new GenericalError();
                        genericalError.setErrorType(Enums.ErrorType.ERROR);
                        if(error!= null && error.networkResponse!=null && error.networkResponse.data!=null){
                            String description = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                            genericalError.setDescripcion(description);
                            if(onDataResultInterface != null){
                                onDataResultInterface.OnError(genericalError);
                            }
                        }else{
                            genericalError.setErrorType(Enums.ErrorType.FATAL);
                            genericalError.setDescripcion("ERROR SERVER DESCONOCIDO");
                        }

                        if(Constants.DEBUG){
                            Log.d(TAG,error.toString());
                        }


                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {


                if(headers!=null){
                    return  headers;
                }else{
                    return super.getHeaders();
                }

            }
        };
        mRequestQueue.add(getRequest);



    }


    public void postRequestWithParams(final Object request, String method, final Class requestClass, final Enums.ResultType type, final Map<String, String> headers){


        final RequestQueue mRequestQueue = Volley.newRequestQueue(context);

        String url = new StringBuilder(Constants.BASE_URL).append(method).toString();


        StringRequest getRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {

                        if(Constants.DEBUG){
                            Log.d(TAG,response);
                        }

                        processResponse(requestClass,response);
                        mRequestQueue.stop();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        mRequestQueue.stop();
                        GenericalError genericalError = new GenericalError();
                        genericalError.setErrorType(Enums.ErrorType.ERROR);
                        if(error!= null && error.networkResponse!=null && error.networkResponse.data!=null){
                            String description = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                            genericalError.setDescripcion(description);
                            if(onDataResultInterface != null){
                                onDataResultInterface.OnError(genericalError);
                            }
                        }else{
                            genericalError.setErrorType(Enums.ErrorType.FATAL);
                            genericalError.setDescripcion("ERROR SERVER DESCONOCIDO");
                        }

                        if(Constants.DEBUG){
                            Log.d(TAG,error.toString());
                        }

                    }
                }






        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  lparams = new HashMap<String, String>();
                lparams.put("Content-Type", "application/json; charset=utf-8");

                if (headers!=null){
                    return  headers;
                }else{
                    return  lparams;
                }
            }



            @Override
            public byte[] getBody() throws AuthFailureError {

                Gson gson = new Gson();
                String jsonString = gson.toJson(request);
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(jsonString);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return  jsonObject.toString().getBytes();
            }


            @Override
            public String getBodyContentType() {
                return "application/json";

            }
        };
        mRequestQueue.add(getRequest);



    }



}
