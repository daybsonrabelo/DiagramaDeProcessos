package com.nossomundogp.diagramadeprocessos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A fragment representing a single step in a wizard. The fragment shows a dummy title indicating
 * the page number, along with some dummy text.
 *
 * <p>This class is used by the {@link CardFlipActivity} and {@link
 * ScreenSlideActivity} samples.</p>
 */
public class ScreenSlidePageFragment extends Fragment {
    /**
     * The argument key for the page number this fragment represents.
     */
    public static final String ARG_PAGE = "page";

    /**
     * The fragment's page number, which is set to the argument value for {@link #ARG_PAGE}.
     */
    private int mPageNumber;

    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static ScreenSlidePageFragment create(int pageNumber) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ScreenSlidePageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout containing a title and body text.
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_screen_slide_page, container, false);

        // Set the title view to show the page number.
        if ((mPageNumber + 1) == 1) {
        	((ImageView) rootView.findViewById(R.id.imgMudar)).setImageResource(R.drawable.step1);
        	 ((TextView) rootView.findViewById(android.R.id.text2)).setText(R.string.step1);
        } else if ((mPageNumber + 1) == 2) {
        	((ImageView) rootView.findViewById(R.id.imgMudar)).setImageResource(R.drawable.step2);
        	((TextView) rootView.findViewById(android.R.id.text2)).setText(R.string.step2);
        } else if ((mPageNumber + 1) == 3) {
        	((ImageView) rootView.findViewById(R.id.imgMudar)).setImageResource(R.drawable.step3);
        	((TextView) rootView.findViewById(android.R.id.text2)).setText(R.string.step3);
        } else if ((mPageNumber + 1) == 4) {
        	((ImageView) rootView.findViewById(R.id.imgMudar)).setImageResource(R.drawable.step4);
        	((TextView) rootView.findViewById(android.R.id.text2)).setText(R.string.step4);
        } else if ((mPageNumber + 1) == 5) {
        	((ImageView) rootView.findViewById(R.id.imgMudar)).setImageResource(R.drawable.step5);
        	((TextView) rootView.findViewById(android.R.id.text2)).setText(R.string.step5);
        } else if ((mPageNumber + 1) == 6) {
        	((ImageView) rootView.findViewById(R.id.imgMudar)).setImageResource(R.drawable.step6);
        	((TextView) rootView.findViewById(android.R.id.text2)).setText(R.string.step6);
        } else {
        	((ImageView) rootView.findViewById(R.id.imgMudar)).setImageResource(R.drawable.step7);
        	((TextView) rootView.findViewById(android.R.id.text2)).setText(R.string.step7);
        }
        
        ((TextView) rootView.findViewById(android.R.id.text1)).setText(
                getString(R.string.title_template_step, mPageNumber + 1));

        return rootView;
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return mPageNumber;
    }
}
