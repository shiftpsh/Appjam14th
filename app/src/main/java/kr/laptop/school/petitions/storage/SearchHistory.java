package kr.laptop.school.petitions.storage;

import android.content.Context;
import android.content.SharedPreferences;

public class SearchHistory {

    private Context c;
    private SharedPreferences s;

    public SearchHistory(Context c) {
        this.c = c;
        this.s = c.getSharedPreferences("history", Context.MODE_PRIVATE);
    }

    private int getHistoryCount() {
        return s.getInt("count", 0);
    }

    private void incrementHistoryCount() {
        SharedPreferences.Editor e = s.edit();
        e.putInt("count", getHistoryCount() + 1);
        e.commit();
    }

    public void deleteAll() {
        SharedPreferences.Editor e = s.edit();
        e.clear();
        e.commit();
    }

    public void addSearchHistory(String str) {
        SharedPreferences.Editor e = s.edit();
        e.putString("history-" + getHistoryCount(), str);
        incrementHistoryCount();
        e.commit();
    }

    public String[] getRecentSearches() {
        int count = getHistoryCount();
        switch (count) {
            case 0:
                return new String[0];
            case 1:
                return new String[]{
                        s.getString("history-0", "")
                };
            case 2:
                return new String[]{
                        s.getString("history-0", ""),
                        s.getString("history-1", "")
                };
            case 3:
                return new String[]{
                        s.getString("history-0", ""),
                        s.getString("history-1", ""),
                        s.getString("history-2", "")
                };
            case 4:
                return new String[]{
                        s.getString("history-0", ""),
                        s.getString("history-1", ""),
                        s.getString("history-2", ""),
                        s.getString("history-3", "")
                };
            default:
                return new String[]{
                        s.getString("history-" + (count - 5), ""),
                        s.getString("history-" + (count - 4), ""),
                        s.getString("history-" + (count - 3), ""),
                        s.getString("history-" + (count - 2), ""),
                        s.getString("history-" + (count - 1), "")
                };
        }
    }
}
