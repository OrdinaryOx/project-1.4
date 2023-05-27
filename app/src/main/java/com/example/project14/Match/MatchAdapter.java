package com.example.project14.Match;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.project14.R;

import java.util.ArrayList;
import java.util.List;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Match> matches;

    public MatchAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.matches = createDummyData();
    }

    @NonNull
    @Override
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = layoutInflater.inflate(R.layout.item_match,
                parent, false);
        return new MatchViewHolder(mItemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {
        Match match = matches.get(position);

        holder.username.setText(match.getName());
        holder.age.setText(match.getAge());
        Glide.with(holder.profileImage)
                .load(match.getImageURL())
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(holder.profileImage);
    }

    @Override
    public int getItemCount() {
       return matches.size();
    }


    private List<Match> createDummyData() {
        List<Match> dummyData = new ArrayList<>();

        // Create and add dummy Chat objects to the list
        dummyData.add(new Match("User 1", 91, "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIEAAACBCAMAAADQfiliAAAAMFBMVEXk5ueutLepsLPq7O3n6erZ3N3R1Na4vcC7wMLFycuzubzU19m+w8XKztDO0dPd4OFRhiliAAAC00lEQVR4nO2aXZOrIAxA+QgiiPL//+1i3V7n2oqJTejMDueh3X3iNCQIQaU6nU6n0+l0Op1O508C8P938+HdnEZfGMOSm0uAGpIx+kn5MzjbdPxZv2Di1C4QSzSvBsXBuyYKoPzb8R8OcwMFmE7Hf4RBXmCpCaxk2TBcCxQFUYHqFDyRFHAYAR0FFSJGQJsklQqQUCEoCpOQAioJNmQMLHp8rYOEAqYQdwQEsGm4YQSCgKzEJwIVCSNFoJQDu0EmCWg9sm9YCKW4wS0AgSigHbOBpVTCilm4q4EaAs39cKAmotaeORUd2UAzG0xkAcM8CwO1GLuBgMGNPPhztfD99QCoAux7FOupBgOvgIKZWgzcz0ZF26RpiQ077fEssFWFQAqCYZ+EUo8kA4lOBmmzbAaJQxMlCDIHeIsPgtThGb8yc6/IT/CrklgrCUaUgkwa/oJZliTOzTuIVDBJcHyMgvHSjd38vqu9R6BBZ7nWUeM/Lb4DltPxo3BT+Z9CPglDkwBs2Jy0Of7+pe1dE6gpRW3Wu6b1wwfX/sKtjJjdMocwDy6rL9z3AYDdgaYRKCMrN80h+bhNgY5+fEQCrLzHGvn5sU8wx0xc/49hEp0QUG728Tj00aNYKBELsC7UR9819Diw54XN83EBqEuYxFmeJfojafwNP3BNBrjzS9Z6ICKLA2Tc1uzEYfp0zwrVZzHK4bPHJdDbR68O4ZOp+DAAvwrx7ksBkG8UwHuH5VY2AL2FeK5wZ/9M7BdcKdBP8+gbXizU0zTyfEaClI7sEVihRIHeukMx4gXIN3s48OcpWs+KooA81gP1Yo+ggBMg3fITQaUCvY9PANPishKFuBOvDURDgDne2yRrgFgaRedAX78acd6dYONqWaK963GHqzVBehIup4F8l3ODasMT3rwBy051uwTitViI1b439VbxFjWDvPZExKlegtkW1AQ6nU6n811+APDtH/IjMI1UAAAAAElFTkSuQmCC"));
        dummyData.add(new Match("User 2", 89,  "data:image/jpeg;base64,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"));
        dummyData.add(new Match("User 3", 72, "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBwgHBgkIBwgKCgkLDRYPDQwMDRsUFRAWIB0iIiAdHx8kKDQsJCYxJx8fLT0tMTU3Ojo6Iys/RD84QzQ5OjcBCgoKDQwNGg8PGjclHyU3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3N//AABEIAIMAVwMBIgACEQEDEQH/xAAbAAABBQEBAAAAAAAAAAAAAAAGAAMEBQcCAf/EADgQAAIBAwIEBAIHBwUAAAAAAAECAwAEEQUhBhIxQRNRYXEigQcyQlKxwdEUIySRkuHwFhdig6H/xAAYAQADAQEAAAAAAAAAAAAAAAAAAQIDBP/EAB4RAQEAAgMAAwEAAAAAAAAAAAABAhEDIUESMTJR/9oADAMBAAIRAxEAPwAkjjqQiUkWnkWqS8CV0EpwDbpXWKAZKelNslSitcMtAQnj9KjyR+lWDrTEi0BVyxVCmi9KuJEqJLHTCkmh36UqnTRelKghQi08orlRTqikp0BTd1cQWcDTXUiRRr1ZjgU6SApJOBWS6/xJJq3EH8Pyy2loxCIDnLD7WO/+edK3RybXt3xdfandSW2lW720Ee7zHeVl8wvYfzOPKpVtd38kiPHqeUCg8rAFJB5g9QfehmKVbqZbqzuora5A5GjlX4Tv0PcVQa3cXMd2VgjaGcH4jbylkJPp2rG22/bWSTwfz8bJptwLfVodtsTRdGXzA7/I/pRFZ31pqNus9lOk0bd1PSsJmvL6SHwrlGkjDcwDr0PcirXgi6a31tGSUxBmA5QxAbtitMbfWeUbHItRpFqawyKYkWtEK+SOvKkSLSoC7WnFppTTq0Gg8Rzm10DUJlblKQMQ3ltWLcKaDea5qBjtXMcaHDyCtj4whE/CuqIQT/DOwA8wMj8KFPouvrLStKkaYg3DyEmJQSyjtnyyN96y5N66acf2ItG+j6GFVFzJLOcdXcmr6Thayjj5UtYwBjtVK/0jpb3fgy2Rjj+/4qnA9R1qfqPHdvbWokXklZhlUVxlqx1/W+74hapw1Zyks1sgAB6Csa4gt10TWpY4SQrbgfjWl/7i/tDstxpxiQbcyzK/4Gsx4xuEutRNxE/MjZIOenvTwlmSeSy4tf4auHu+HdPnkYs7wgknuamuKgcKRGDhvToX+usC83uRn86sXrpcqJIKVduNqVMLNelOLTKmnVNBm9Uia40y6hRirPEygjscUI8I8PWmoaUVlWQLKoJdckl1JU82N+nJ/I0bDpv0oYXWE0i7uoJkI5XZxgYyp3BrLkbcPbibgjTbZ1KWwM7MBGqRyJlu2STgDz9M9a94t4M0S2h0mO1s0gwhjmkgjw8ucAF8bk5HXrua7/1P4elTa/LaSSQ27AxwKRlgSAW+WSR7VV6/9JGk6ubS0it5XEsq5cjlMXYN7jNZbvja44+ol1wTbxuXtTyA4zhpN8f8cUE8Q2D2Mng5I8eQu6nuB9X223+daXccTQ2/iWxctIgyCPtDzrNtduzd6g9xK+eXpnuTv+lPC21PJJI03h/UBNGik78oq6fptWX8L6my3EYJrSoZRLGG9K6HI5eva8fpSphPQ06pqOhp1TQZ8NQHxpIkGtW0sq80UqGKQE9Rn+5o4ztQNx1F408anuG/mATU5TcPG6yiRJw7BNpniWl9NBFKn7yNhzqfzoCl0W2W/cW9yrYb7nSizhbjaKxt/wBj1OLm5Ng5GzU5qfFOjorTW1pH4jfFhVxk1zdzp1fLGwN6stlpMYiiMkl3JH8byH6ucZwOg2/GhV255WbsTtVncSyapqMk8y4Genp2FVS1txzTDO7XGhEibI7Vp+jXPPAqk7gVmmhAc3zo50iXw2ArVl6I2OaVM+JkUqAno1PK1Q43qTAryuqRqWY9ABSM7naqDXLYzzRvykqucn5Y/OjzT9AwFe9P/Wv5mq3i2zELJ4aAREbY6Co5LrFeE3kxzVtGIlYxfCc7VSzWF0Hw2OU+laJfwAqSR0qguYV5+lYzK6dGWEqmtbIryoO27GocuhXTSn9nAZSdgTjFFFnb8z7CiLRtIM1yicvVsk46ClM7L0WWE12A7GyutOnEV7A8LncBx19j3ontH5cGtJfTre5Tw54UkTGMMM1U33BkLqX09zC33G3X+1dUrksUsE2VryuLmxvdOPLdQso7MN1PzpUwI9G0ue/xIf3cH3j1PtRjp1jb2KARJ8Xdj1NcRsqKAoAA2AFPCUVO1aTAwIqFfwR3EDQzrzRny6j1FdGXPSm3lbzpAHarwxMSz2jrKp9cGhl+GL5pvjifl9FNaZIQx6b+Y2qM6HP1n/qNReONZy2BfSuGmi+KVAg832xRNbWsFpHywjc/WcjdvavFXlYEdfM70+BtvTmEiMs7kUYAp9aY6V4ZeU1ST8qI4wygjyIpVEluQqKSepxSoDxbsNGJVzhhsPI+VPQuz9aGrK9Bupbc9CPGQ+h6/r86vLW4BQYoC0U4FeO1MLLmuZJlVSzMAo6k0B2a4em3YsNm5fUdf/ah3FtE6M01zdAAZJFyyAf0kCgJhroNtQ3Nc6dZMEGvSQMcECW6EgPf7edqnWurQTSpCs0UpdSUkiYMr469zg+lL5BaM1R5nGDv2riSfbrUC7uRyMCeopg5PLzSQID1J/ClQ/qWtRadbR3cnxBCRgdTnb/PalQFdo00hn0Yljl4Creo5T+goxsyfDG9KlQE1mIjGDVfxEzLbWwBIBnGfXY0qVPH9RHJ+as3Jyd6DfpIuJo9NhiSRlSSTDgfawKVKoyXGbzzSSFUdvhUDYADOw3Pmfem7eeW2uUlt5HjcEYZDg0qVSGw2M8k9hHJK3M5LAnGOjEflUHUnYRNvSpVc+gE78+NZWay/EP2h+vs1eUqVMn/2Q=="));
        // Add more dummy data as needed

        return dummyData;
    }
    class MatchViewHolder extends RecyclerView.ViewHolder {
        public TextView username;
        public TextView age;
        public ImageView profileImage;

        public MatchViewHolder(@NonNull View ItemView) {
            super(ItemView);
            this.username = ItemView.findViewById(R.id.match_username);
            this.age = ItemView.findViewById(R.id.match_age);
            this.profileImage = ItemView.findViewById(R.id.match_pfp);
        }

    }
}
