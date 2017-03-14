package com.example.user.fragmenttablayout.Fragment.Home.Home;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.user.fragmenttablayout.Adapter.InfoStoreWindowAdapter;
import com.example.user.fragmenttablayout.Adapter.MenuHeaderMapAdapter;
import com.example.user.fragmenttablayout.Network.ApiType;
import com.example.user.fragmenttablayout.Network.CallApi;
import com.example.user.fragmenttablayout.Network.CallBackData;
import com.example.user.fragmenttablayout.Object.DanhMuc;
import com.example.user.fragmenttablayout.Object.DiaDiem;
import com.example.user.fragmenttablayout.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by User on 9/6/2016.
 */

public class FragmentMap extends Fragment implements OnMapReadyCallback, CallBackData, CheckDanhMuc, GoogleMap.OnInfoWindowClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private View rootView;
    private RecyclerView recyclerView;
    private GoogleMap mgoogleMap;
    private static final int ZOOM = 15;
    DiaDiem diaDiem = new DiaDiem();
    List<DiaDiem> diaDiemList = new LinkedList<>();
    List<DanhMuc> danhMucList = new LinkedList<>();
    Marker marker;
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_map, null);
        InitView();
        return rootView;
    }


    private void InitView() {
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map_view);
        mapFragment.getMapAsync(this);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recylerview_map);
    }


    private void DumbData() {
        LatLng store = new LatLng(-34, 151);
        for (int i = 0; i < diaDiemList.size(); i++) {
            store = new LatLng(Double.parseDouble(String.valueOf(diaDiemList.get(i).lat)), Double.parseDouble(String.valueOf(diaDiemList.get(i).lng)));
            Marker marker = mgoogleMap.addMarker(new MarkerOptions().position(store).title(diaDiemList.get(i).ten).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
            marker.setTag(diaDiemList.get(i));
        }
        mgoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(store, ZOOM));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mgoogleMap = googleMap;
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mgoogleMap.setMyLocationEnabled(true);
            }
        } else {
            buildGoogleApiClient();
            mgoogleMap.setMyLocationEnabled(true);
        }
        mgoogleMap.setInfoWindowAdapter(new InfoStoreWindowAdapter(getContext()));
        CallApi.getInstance().SetcallBack(this);
        CallApi.getInstance().CallapiServer(ApiType.GET_DIADIEM, null, null);
        CallApi.getInstance().CallapiServer(ApiType.GET_DANHMUC, null, null);
        mgoogleMap.setOnInfoWindowClickListener(this);
    }

    @Override
    public void Callback(ApiType apiType, String json) {
        if (apiType == ApiType.GET_DIADIEM) {
            try {
                diaDiemList = parseDiaDiem(json);
                DumbData();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (apiType == ApiType.GET_DANHMUC) {
            try {
                danhMucList = parseDanhMucs(json);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(new MenuHeaderMapAdapter(getContext(), danhMucList, this));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public List<DanhMuc> parseDanhMucs(String json) throws JSONException {
        List<DanhMuc> danhMucs = new LinkedList<>();
        JSONArray listDanhmuc = new JSONArray(json);
        for (int i = 0; i < listDanhmuc.length(); i++) {
            JSONObject node = listDanhmuc.getJSONObject(i);
            DanhMuc danhMuc = new DanhMuc();
            danhMuc.ten = node.getString("Ten");
            danhMuc.id = node.getInt("Id");
            danhMucs.add(danhMuc);
        }
        return danhMucs;
    }

    public List<DiaDiem> parseDiaDiem(String json) throws JSONException {
        List<DiaDiem> diaDiems = new LinkedList<>();
        JSONArray listDiaDiem = new JSONArray(json);
        for (int i = 0; i < listDiaDiem.length(); i++) {
            JSONObject node = listDiaDiem.getJSONObject(i);
            DiaDiem diaDiem = new DiaDiem();
            diaDiem.ten = node.getString("Ten");
            diaDiem.diachi = node.getString("diachi");
            diaDiem.anh = node.getString("Anh");
            diaDiem.luotcomment = node.getInt("luotComment");
            diaDiem.lat = node.getDouble("lat");
            diaDiem.lng = node.getDouble("lng");
            JSONObject nodeDanhMuc = node.getJSONObject("DanhMuc");
            DanhMuc danhMuc = new DanhMuc();
            danhMuc.id = nodeDanhMuc.getInt("Id");
            danhMuc.ten = nodeDanhMuc.getString("Ten");
            diaDiem.danhMuc = danhMuc;
            diaDiems.add(diaDiem);
        }

        return diaDiems;
    }

    @Override
    public void CheckedCheckBox(int id) {
        if(id == 1){
            DumbData();
        }
        else {
            List<DiaDiem> checkMap = new LinkedList<>();
            for (DiaDiem diaDiem : diaDiemList) {
                if (diaDiem.danhMuc.id == id) {
                    checkMap.add(diaDiem);
                }
            }
            LatLng store = new LatLng(-34, 151);
            mgoogleMap.clear();
            for (int i = 0; i < checkMap.size(); i++) {
                store = new LatLng(checkMap.get(i).lat, checkMap.get(i).lng);
                Marker marker = mgoogleMap.addMarker(new MarkerOptions().position(store).title(checkMap.get(i).ten).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
                marker.setTag(checkMap.get(i));
            }
            mgoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(store, ZOOM));
        }
    }


    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        if (marker != null) {
            marker.remove();
        }

        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        marker = mgoogleMap.addMarker(markerOptions);
        mgoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mgoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15));

        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mgoogleMap.setMyLocationEnabled(true);
                    }

                } else {
                    Toast.makeText(getContext(), "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }
}
