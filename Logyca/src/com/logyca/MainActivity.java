package com.logyca;

import android.app.Activity;
import android.app.ActionBar;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,CambiarEntreFragmentos {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private int navigationCount;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationCount=0;
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        Fragment nuevo;

        Intent i;
        switch (position)
        {
            case 0:
                nuevo = PlaceholderFragment.newInstance(position+1);
                break;
            case 1:
            	nuevo= NoticiaFragment.newInstance(position+1);
            	break;
            case 2:
            	nuevo= TendenciaFragment.newInstance(position+1);
            	break;
            case 3:
            	nuevo= EstandarFragment.newInstance(position+1);
            	break;
            case 4:
            	nuevo = PlaceholderFragment.newInstance(position+1);
            	i = new Intent(MainActivity.this, NFCReaderActivity.class );
    			startActivity(i);
                break;
            case 4:
                nuevo = PlaceholderFragment.newInstance(position+1);
            	i = new Intent(MainActivity.this, QRReaderActivity.class );
    			startActivity(i);
                break;
            default:
                nuevo = PlaceholderFragment.newInstance(position+1);
                break;
        }
        if(navigationCount!=0)
        fragmentManager.beginTransaction().replace(R.id.container,nuevo).addToBackStack("position").commit();
        else
            fragmentManager.beginTransaction().replace(R.id.container,nuevo).commit();
        /*
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();*/
        navigationCount++;
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.Servicios);
                break;
            case 2:
                mTitle = getString(R.string.Noticias);
                break;
            case 3:
                mTitle = getString(R.string.Tendencias);
                break;
            case 4:
                mTitle = getString(R.string.Estandares);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void cambiarFragmento(int fragmento, Bundle bundle) {
        FragmentManager fragmentManager = getFragmentManager();

        switch (fragmento)
        {
            case 0:
                //Mostrar el detalle de un servicio
                ServicioDescripcionFragment detalle;
                detalle = ServicioDescripcionFragment.newInstance(bundle.getString("titulo"), bundle.getString("descripcion"), bundle.getString("link"),bundle.getString("tipo"),bundle.getString("fechaInicio"),bundle.getString("fechaFin"),bundle.getString("hora"),bundle.getString("duracion"),bundle.getString("direccion"),bundle.getString("ciudad"),bundle.getString("pais"),bundle.getString("encargado"));
                fragmentManager.beginTransaction().replace(R.id.container,detalle).addToBackStack(""+fragmento).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                break;
            case 1:
            	//Mostrar el detalle de un servicio
                NoticiaDescripcionFragment detalle2;
                detalle2 = NoticiaDescripcionFragment.newInstance(bundle.getString("titulo"), bundle.getString("descripcion"), bundle.getString("link"));
                fragmentManager.beginTransaction().replace(R.id.container,detalle2).addToBackStack(""+fragmento).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                break;
            case 2:
            	//Mostrar el detalle de una tendencia
            	TendenciaDescripcionFragment detalle3;
            	detalle3 = TendenciaDescripcionFragment.newInstance(bundle.getString("titulo"), bundle.getString("descripcion"), bundle.getString("link"));
                fragmentManager.beginTransaction().replace(R.id.container,detalle3).addToBackStack(""+fragmento).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                break;
            case 3:
            	//Mostrar el menu de servicios
                Fragment nuevo;
                nuevo = PlaceholderFragment.newInstance(1);
                fragmentManager.beginTransaction().replace(R.id.container,nuevo).commit();
                break;  
            case 4:
            	//Mostrar el detalle de una tendencia
            	TendenciaDescripcionFragment detalle5;
            	detalle5 = TendenciaDescripcionFragment.newInstance(bundle.getString("titulo"), bundle.getString("descripcion"), bundle.getString("link"));
                fragmentManager.beginTransaction().replace(R.id.container,detalle5).addToBackStack(""+fragmento).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                break;
            default:
                break;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        CambiarEntreFragmentos mListener;
        ArrayList<Servicio> servicios;


        private void loadServices()
        {
            String serviciosJSON= "{\"servicios\":[{\"nombreServicio\":\"serv1\",\"descripcionServicio\":\"blah\",\"nombreTipoServicio\":\"formacion\",\"fechaInicio\":\"12-12-12\",\"fechaFin\":\"12-12-12\",\"hora\":\"12pm\",\"duracion\":\"2h\",\"direccion\":\"alguna dir\",\"ciudad\":\"Bogota\",\"pais\":\"Colombia\",\"encargado\":\"Yohany\",\"enlace\":\"www.facebook.com\"},{\"nombreServicio\":\"serv2\",\"descripcionServicio\":\"blah\",\"nombreTipoServicio\":\"formacion\",\"fechaInicio\":\"13-13-13\",\"fechaFin\":\"13-13-13\",\"hora\":\"3am\",\"duracion\":\"15min\",\"direccion\":\"123 fake st\",\"ciudad\":\"Bogoshit\",\"pais\":\"Coloshit\",\"encargado\":\"Yohashit\",\"enlace\":\"www.9gag.com\"}]}";
            JSONParser parser = new JSONParser();
            JSONObject jOb = new JSONObject();
            try {
                jOb =  (JSONObject) parser.parse(serviciosJSON);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            JSONArray servs = (JSONArray)jOb.get("servicios");
            for (int i = 0; i < servs.size() ; i++) {
                JSONObject auxNew = (JSONObject)servs.get(i);
                servicios.add(new Servicio(((String)auxNew.get("nombreServicio")),((String)auxNew.get("descripcionServicio")),((String)auxNew.get("enlace")),((String)auxNew.get("nombreTipoServicio")),((String)auxNew.get("fechaInicio")),((String)auxNew.get("fechaFin")),((String)auxNew.get("hora")),((String)auxNew.get("duracion")),((String)auxNew.get("direccion")),((String)auxNew.get("ciudad")),((String)auxNew.get("pais")),((String)auxNew.get("encargado"))));
            }

        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));

            //Obtener los servicios de verdad
            servicios = new ArrayList<Servicio>();
            loadServices();
            //Poner adaptador
            ServicioAdapter adaptador = new ServicioAdapter(getActivity(), servicios);


            //Lista en el layout...
            ListView listaServicios;
            listaServicios = (ListView) rootView.findViewById(R.id.ServicesListView);
            //Ponerle el adaptador
            listaServicios.setAdapter(adaptador);
            listaServicios.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    //Toast.makeText(getActivity(), "Clicked position: "+position, Toast.LENGTH_SHORT).show();
                    Bundle elBundle = new Bundle();
                    Servicio serv = servicios.get(position);
                    elBundle.putString("titulo",serv.getTitulo());
                    elBundle.putString("descripcion",serv.getDescripcion());
                    elBundle.putString("link",serv.getLink());
                    elBundle.putString("tipo",serv.getTipo());
                    elBundle.putString("fechaInicio",serv.getFechaInicio());
                    elBundle.putString("fechaFin",serv.getFechaFin());
                    elBundle.putString("hora",serv.getHora());
                    elBundle.putString("duracion",serv.getDuracion());
                    elBundle.putString("direccion",serv.getDireccion());
                    elBundle.putString("ciudad",serv.getCiudad());
                    elBundle.putString("pais",serv.getPais());
                    elBundle.putString("encargado",serv.getEncargado());
                    mListener.cambiarFragmento(0,elBundle);
                }
            });

            Servicio nuevo = new Servicio("Consultoria","Haga uso de este servicio para poder dar fin a todos sus dolores de cabeza con respecto a consutoria", "ESTE ES EL LINK");
            Servicio nuevo2 = new Servicio("Asesoria","Cada que piense en asesores, piense en Logyca, tenemos una amplia gama de charlatanes dispuestos a cobrar lo que no se gana su empresa, solamente por criticar!", "ESTE ES EL LINK");
            Servicio nuevo3= new Servicio("Logistica a su puert","Ha pensado en logistica como piensa en pizza ??? si usted es así, con Logyca deberia quedarse!", "ESTE ES EL LINK");
            Servicio nuevo4 = new Servicio("Fin","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus nunc ligula, feugiat ut accumsan id, ultricies eu lorem. Aenean dapibus, odio nec aliquam blandit, velit ante faucibus urna, et dapibus enim neque et nibh. Suspendisse vitae leo augue. Sed quis suscipit elit, sed gravida justo. Phasellus a suscipit ligula, at tempus ligula. Proin eget sagittis neque, vel dapibus tellus. Quisque laoreet iaculis risus, ut posuere ligula vehicula nec. Vestibulum eu vestibulum est. Phasellus condimentum eros ac bibendum feugiat. Vivamus hendrerit mauris non nisl aliquam, ac malesuada elit auctor. Duis mollis commodo ullamcorper. Sed molestie congue volutpat. Cras convallis metus a aliquam condimentum. Fusce tincidunt elit a lorem suscipit dignissim. Sed sed quam pretium, semper urna ac, ullamcorper erat. Cras pulvinar tincidunt augue, sit amet scelerisque odio elementum aliquam.", "ESTE ES EL LINK");

            servicios.add(nuevo);
            servicios.add(nuevo2);
            servicios.add(nuevo3);
            servicios.add(nuevo4);
            servicios.add(nuevo4);





            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            try {
                mListener = (CambiarEntreFragmentos) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString()
                        + " must implement CambiarEntreFragmentos");
            }
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
