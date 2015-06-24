package com.dequesystems.accessibility101.tabbednavigation;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.dequesystems.a11yframework.TabLayout;
import com.dequesystems.accessibility101.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabbedNavigationAboutFragment extends Fragment{

    private TabHost mTabHost;


    TextView mTextView1;


    public TabbedNavigationAboutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_tabbed_navigation_about, container, false);

        mTextView1 = (TextView) view.findViewById(R.id.aacTabNavTextView1);

        mTextView1.setMovementMethod(LinkMovementMethod.getInstance());

        int linkColor = getResources().getColor(R.color.aac_text_link);

        mTextView1.setLinkTextColor(linkColor);

        mTabHost = (TabHost) view.findViewById(R.id.tabNavAboutTabHost);
        mTabHost.setup();

        mTabHost.addTab(mTabHost.newTabSpec("tab1").setContent(R.id.tab1).setIndicator(createTabIndicator(mTabHost.getContext(), R.string.aac_tab_nav_cat_tab_title)));
        mTabHost.addTab(mTabHost.newTabSpec("tab2").setContent(R.id.tab1).setIndicator(createTabIndicator(mTabHost.getContext(), R.string.aac_tab_nav_dog_tab_title)));
        mTabHost.addTab(mTabHost.newTabSpec("tab3").setContent(R.id.tab1).setIndicator(createTabIndicator(mTabHost.getContext(), R.string.aac_tab_nav_fish_tab_title)));

        TextView textView = (TextView) mTabHost.getCurrentTabView().findViewById(R.id.aac_tab_nav_tab_title);
        textView.setTextColor(getResources().getColor(R.color.aac_tab_bar_selected));

        ImageView imageView = (ImageView) view.findViewById(R.id.aac_tab_nav_about_image_view);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.cat));
        imageView.setContentDescription(getResources().getString(R.string.aac_cont_desc_fixed_cat_cont_desc));


        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                ImageView imageView = (ImageView) view.findViewById(R.id.aac_tab_nav_about_image_view);
                Drawable newImage;
                String contDesc;

                if (mTabHost.getCurrentTab() == 0) {
                    newImage = getResources().getDrawable(R.drawable.cat);
                    contDesc = getResources().getString(R.string.aac_cont_desc_fixed_cat_cont_desc);
                } else if (mTabHost.getCurrentTab() == 1) {
                    newImage = getResources().getDrawable(R.drawable.dog);
                    contDesc = getResources().getString(R.string.aac_cont_desc_fixed_dog_cont_desc);
                } else {
                    newImage = getResources().getDrawable(R.drawable.fish);
                    contDesc = getResources().getString(R.string.aac_cont_desc_fixed_fish_cont_desc);
                }

                imageView.setImageDrawable(newImage);
                imageView.setContentDescription(contDesc);

                int tab = mTabHost.getCurrentTab();
                for (int i = 0; i < mTabHost.getTabWidget().getTabCount(); i++) {

                    TextView textView = (TextView) mTabHost.getTabWidget().getChildAt(i).findViewById(R.id.aac_tab_nav_tab_title);

                    int color;
                    if (i == tab) {
                        color = getResources().getColor(R.color.aac_demo_tab_bar_selected);
                    } else {
                        color = getResources().getColor(R.color.aac_demo_tab_bar_dimmed);
                    }

                    if(textView != null) textView.setTextColor(color);
                }

            }

        });

        return view;
    }

    private View createTabIndicator(Context context, int title) {
        TabLayout view = (TabLayout) LayoutInflater.from(context).inflate(R.layout.tab_nav_story_tab_layout_fixed, null);
        view.setTabHost(mTabHost);
        TextView tv = (TextView) view.findViewById(R.id.aac_tab_nav_tab_title);
        tv.setText(title);
        return view;
    }

}