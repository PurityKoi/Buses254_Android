package com.example.user.buses254;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;

import Adapter.FeedAdapter;
import Common.ErrorTracker;
import Common.HTTPDataHandler;
import Common.RecyclerItemClickListener;
import Model.RssObject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewsFeedFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewsFeedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFeedFragment extends Fragment implements View.OnClickListener

{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public NewsFeedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsFeedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsFeedFragment newInstance(String param1, String param2) {
        NewsFeedFragment fragment = new NewsFeedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View v) {
        loadRss();
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

   /* @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    //REQUIRED VARIABLES
    ImageButton btRefresh;
    Toolbar toolbar;
    RecyclerView recyclerView;
    RssObject rssObject;
    SwipeRefreshLayout mSwipeLayout;
    FeedAdapter feedAdapter;
    //RSS Link
    final String RSS_Link = "http://rss.cnn.com/rss/edition_africa.rss";
    final String RSS_to_JSON_API = " https://api.rss2json.com/v1/api.json?rss_url=";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_feed, container, false);

        toolbar = (Toolbar) view.findViewById(R.id.newsfeedToolbar);
        toolbar.setTitle("News");
       // setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) view.findViewById(R.id.newsfeedRecyclerV);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                feedAdapter.OpenObject(position);
            }
        }));


        loadRss();



       btRefresh = (ImageButton) view.findViewById(R.id.btRefresh);
        btRefresh.setOnClickListener(this);

        return view;
    }


    private void loadRss() {
        AsyncTask<String, String, String> loadRSSAsync = new AsyncTask<String, String, String>() {
            ProgressDialog progressDialog = new ProgressDialog(getActivity());

            @Override
            protected void onPreExecute() {
                progressDialog.setMessage("Please Wait...");
                progressDialog.show();
            }

            @Override
            protected String doInBackground(String... params) {
                String result;
                HTTPDataHandler http = new HTTPDataHandler();
                result = http.GetHTTPData(params[0]);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                if (s == ErrorTracker.Connection_Error) {
                    Toast.makeText(getActivity(), " Connection Error", Toast.LENGTH_LONG).show();

                } else if (s == ErrorTracker.IO_Error) {
                    Toast.makeText(getActivity(), " Read Error", Toast.LENGTH_LONG).show();

                } else if (s == ErrorTracker.Response_Error) {
                    Toast.makeText(getActivity(), " Response Error", Toast.LENGTH_LONG).show();

                } else if (s == ErrorTracker.Wrong_URL_Format) {
                    Toast.makeText(getActivity(), " Wrong URL Format", Toast.LENGTH_LONG).show();

                } else {
                    progressDialog.dismiss();
                    rssObject = new Gson().fromJson(s, RssObject.class);
                    feedAdapter = new FeedAdapter(rssObject, getActivity());
                    recyclerView.setAdapter(feedAdapter);
                    feedAdapter.notifyDataSetChanged();
                }
            }
        };

        StringBuilder url_get_data = new StringBuilder(RSS_to_JSON_API);
        url_get_data.append(RSS_Link);
        loadRSSAsync.execute(url_get_data.toString());
        
    }

}
