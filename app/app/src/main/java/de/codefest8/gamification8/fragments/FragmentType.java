package de.codefest8.gamification8.fragments;

import android.content.Context;

import de.codefest8.gamification8.R;

/**
 * Created by koerfer on 07.03.2015.
 */
public enum FragmentType {
    Home,
    FriendList,
    FriendDetail,
    TrackHistory,
    TrackDetail,
    AchievementList,
    About;

    public static String getFragmentTitle(Context context, FragmentType type)
    {
        return context.getResources().getStringArray(R.array.fragments_titles)[type.ordinal()];
    }
}
