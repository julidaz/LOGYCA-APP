package com.logyca;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;

public class myLocationAsyncTask extends AsyncTask<Void, Void, Void> implements LocationListener {
	private Context ContextAsync;
	public myLocationAsyncTask (Context context){
		this.ContextAsync = context;
	}

	Dialog progress;
	private String providerAsync;
	private LocationManager locationManagerAsync;  
	double   latAsync=0.0;
	double lonAsync=0.0;
	String thikanaAsync="Scan sms for location";
	boolean GPSActivo=true;

	String AddressAsync="";
	Geocoder GeocoderAsync;

	Location location;

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progress = ProgressDialog.show(ContextAsync, "Loading data", "Please wait...");

	}

	@Override
	protected Void doInBackground(Void... arg0) {
		// TODO Auto-generated method stub
		locationManagerAsync = (LocationManager) ContextAsync.getSystemService(ContextAsync.LOCATION_SERVICE);


		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_COARSE);
		criteria.setCostAllowed(false);
		criteria.setPowerRequirement(Criteria.NO_REQUIREMENT);
		providerAsync = locationManagerAsync.getBestProvider(criteria, false);


		if (locationManagerAsync.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			providerAsync = LocationManager.GPS_PROVIDER;
		} else if (locationManagerAsync.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			GPSActivo=false;
			providerAsync = LocationManager.NETWORK_PROVIDER;
		} else if (locationManagerAsync.isProviderEnabled(LocationManager.PASSIVE_PROVIDER)) {
			providerAsync = LocationManager.PASSIVE_PROVIDER;
			//Toast.makeText(ContextAsync, "Switch On Data Connection!!!!", Toast.LENGTH_LONG).show();
		}    

		location = locationManagerAsync.getLastKnownLocation(providerAsync);
		// Initialize the location fields
		if (location != null) {
			//  System.out.println("Provider " + provider + " has been selected.");
			latAsync = location.getLatitude();
			lonAsync = location.getLongitude();

		} else {
			//Toast.makeText(ContextAsync, " Locationnot available", Toast.LENGTH_SHORT).show();
		}

		List<Address> addresses = null;
		GeocoderAsync = new Geocoder(ContextAsync, Locale.getDefault());
		try {
			addresses = GeocoderAsync.getFromLocation(latAsync, lonAsync, 1);

			String address = addresses.get(0).getAddressLine(0);
			String city = addresses.get(0).getAddressLine(1);
			String country = addresses.get(0).getCountryName();
			//AddressAsync = Html.fromHtml(address + ", " + city + ",<br>" + country).toString();
			AddressAsync = addresses.get(0).getAddressLine(2)+", "+addresses.get(0).getAddressLine(3);
			AddressAsync.trim();
		} catch (Exception e) {
			e.printStackTrace();
			AddressAsync = "Refresh for the address";
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {  

		if(!GPSActivo)
		{
			AlertDialog.Builder alert = new AlertDialog.Builder(ContextAsync);
			alert.setTitle("GPS is disabled in the settings!");
			alert.setMessage("It is recomended that you turn on your device's GPS and restart the app so the app can determine your location more accurately!");
			alert.setPositiveButton("OK", new OnClickListener()
			{
				public void onClick(DialogInterface dialog, int which) {
					progress.dismiss();
					ExpertoFragment.cargarExpertos("");
				}
			});
			alert.show();
		}
		else
		{
			super.onPostExecute(result);
			progress.dismiss();
			onLocationChanged(location);
			Log.wtf("latAsync_lonAsync",latAsync+"_"+lonAsync);
			Geocoder geocoder=new Geocoder(ContextAsync, Locale.getDefault());
			List<Address> addresses;
			String locationString=null;
			/*
		try {
			addresses = geocoder.getFromLocation(latAsync, lonAsync, 1);

			if(addresses.size()>0)
			{
				locationString=addresses.get(0).getAddressLine(2)+", "+addresses.get(0).getAddressLine(3);
				Log.wtf("pais", "La geoLocalizacion es "+locationString);
			}
			else
				Log.wtf("pais","no pude"); 
		} catch (IOException e) {
			e.printStackTrace();
		}*/
			ExpertoFragment.cargarExpertos(AddressAsync);

			/*
        Intent intentAsync = new Intent(ContextAsync,Emerg.class);
        intentAsync.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);   
        intentAsync.putExtra("calculated_Lat", latAsync);
        intentAsync.putExtra("calculated_Lon", lonAsync);
        intentAsync.putExtra("calculated_address", AddressAsync);

        ContextAsync.startActivity(intentAsync);*/
		}
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		locationManagerAsync.requestLocationUpdates(providerAsync, 0, 0, this);
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
	}
}