package com.example.anthony.clinicplace;

import android.content.Context;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.SearchView;
import android.widget.Toast;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedQueryList;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.example.anthony.clinicplace.dummy.DummyContent;
import com.example.anthony.clinicplace.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;


/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ClinicFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ClinicFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ClinicFragment newInstance(int columnCount) {
        ClinicFragment fragment = new ClinicFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final MapperHelper helper;
        helper = new MapperHelper(getActivity().getApplicationContext());
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        Clinica clinicToFind = new Clinica();
        clinicToFind.setUserid("1");
        Bundle args = getArguments();
        String nombre="";
        if (args!=null) {
                nombre = args.getString("Nombre");
                Condition rangeKeyCondition = new Condition()
                .withComparisonOperator(ComparisonOperator.BEGINS_WITH.toString())
                .withAttributeValueList(new AttributeValue().withS(nombre));
                 DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                .withHashKeyValues(clinicToFind)
                .withRangeKeyCondition("Nombre", rangeKeyCondition)
                .withConsistentRead(false);
                 PaginatedQueryList<Clinica> result = helper.mapper.query(Clinica.class, queryExpression);
            int i=0;
            if (result.size()==0)
            {
                Toast toast1 =
                        Toast.makeText(getActivity().getApplicationContext(),
                                "Clinica no encontrada", Toast.LENGTH_SHORT);
                toast1.show();

            }
             while (i<result.size())
                {
                    Clinica a= result.get(i);
                    nombre= a.getNombre();
                    String sede= a.getSede();
                    DummyContent.addItem(DummyContent.createDummyItem(i,nombre,sede));
                    i++;
                }
        }
        else   {
            PaginatedScanList<Clinica> result = helper.mapper.scan(Clinica.class, scanExpression);
            int i=0;
            while (i<result.size())
            {
                Clinica a= result.get(i);
                nombre= a.getNombre();
                String sede= a.getSede();
                DummyContent.addItem(DummyContent.createDummyItem(i,nombre,sede));
                i++;
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(DummyContent.ITEMS, mListener));

        }


        return view;
    }
        /*
        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            if (context instanceof OnListFragmentInteractionListener) {
                mListener = (OnListFragmentInteractionListener) context;
            } else {
                throw new RuntimeException(context.toString()
                        + " must implement OnListFragmentInteractionListener");
            }
        }
    */
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }

}
