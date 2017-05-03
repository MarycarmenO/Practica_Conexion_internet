import android.widget.TextView;

/**
 * Created by Mary on 03/05/2017.
 */

public class FetchBook extends AsyncTask<String,Void,String>{
    private TextView tituloTexto,autorTitiulo;
    public FetchBook(TextView Titulo,TextView Autor){
        this.autorTitulo=Autor;
        this.tituloTexto=Titulo;}


    @Override
    protected String doInBackground(String... params) {

        return Name.getBookInfo(params[0]);

    }
    protected void onPostExecute(String s){
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray itemsArray = jsonObject.getJSONArray("items");
            //Iterate through the results
            for (int i = 0; i<itemsArray.length();i++){
                JSONObject book = itemsArray.getJSONObject(i);
                String titlle = null;
                String autores = null;
                JSONObject volumeInfo = book.getJSONObject("volumenInfo");
                try {
                    titlle = volumeInfo.getString("title");
                    autores = volumeInfo.getString("authors");
                }catch (Exception e){
                    e.printStackTrace();
                }
                //pero SI un titulo y el autor exiten, actualiza los textviews y lo devuelve
                if (titlle != null && autores != null){
                    tituloTexto.setText(titlle);
                    autorTitiulo.setText(autores);
                    return;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        super.onPostExecute(s);
    }


}
