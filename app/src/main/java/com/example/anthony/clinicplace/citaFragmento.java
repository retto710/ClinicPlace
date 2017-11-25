package com.example.anthony.clinicplace;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link citaFragmento.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link citaFragmento#newInstance} factory method to
 * create an instance of this fragment.
 */
public class citaFragmento extends Fragment {
    private Button guardar;
    private EditText especialidad;
    private EditText horaFin;
    private EditText horaInicio;
    private EditText sede;
    private TransferUtility transferUtility;

    private OnFragmentInteractionListener mListener;

    public citaFragmento() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.);


        final MapperHelper helper = new MapperHelper(getApplicationContext());

        especialidad= (EditText)findViewById(R.id.nombre);
        horaFin=(EditText)findViewById(R.id.apellidos);
        horaInicio=(EditText)findViewById(R.id.dni);
        sede=(EditText)findViewById(R.id.ubicacion);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(nombres)) nombres.setError("Ingrese su nombre");
                else if (isEmpty(apellidos)) nombres.setError("Ingrese sus apellidos");
                else if (isEmpty(dni)) nombres.setError("Ingrese su dni");
                else if (isEmpty(ubicacion)) nombres.setError("Ingrese su distrito");
                else    {



                    Cita objCita = helper.mapper.load(Cita.class, "1",getId());
                    objCita.setEspecialidad(especialidad.getText().toString());
                    objCita.setHoraInicio(horaInicio.getText().toString());
                    objCita.setHoraFin(horaFin.getText().toString());
                    objCita.setSede(sede.getText().toString());
                    helper.mapper.save(objCita);
                    Intent profileIntent = new Intent(citaFragmento.this, AdminProfileActivity.class);
                    startActivity(profileIntent);
                }
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cita_fragmento, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
