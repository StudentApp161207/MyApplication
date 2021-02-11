package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Courselist_y1 extends AppCompatActivity {
    ListView listview;
    int[] pic = {
            R.drawable.python,
            R.drawable.english1,
            R.drawable.tamil,
            R.drawable.maths,
            R.drawable.evs,
            R.drawable.python,
            R.drawable.skb,
            R.drawable.scripture,
            R.drawable.webdesign,
            R.drawable.skb,
            R.drawable.software_engineering};

    String[] course = {"Python Basics & Technics",
            "English",
            "Tamil",
            "Mathematics",
            "Environmental Science",
            "Python Datastructures",
            "Skill Based",
            "Scripture/Value Education",
            "Basics In IT"};

    String detail[] = {"Course code: UCS/CO/73\nCourse Tutor: Ms Mercy Evangeline",
            "Course code: UCS/CO/79M\nCourse Tutor: Ms Regi Thomas",
            "Course code: UCS/CO/73\nCourse Tutor: Dr Narmada V",
            "Course code: UCS/CO/73\nCourse Tutor: Dr Shoba Leslie",
            "Course code: UCS/CO/74\nCourse Tutor: Dr Shoba Leslie",
            "Course code: UCS/CO/80\nCourse Tutor: Ms Regi Thomas",
            "Course code: UCS/CO/71M\nCourse Tutor: Ms Jesilla Malarvizhi",
            "Course code: UCS/CO/75M\nCourse Tutor: Ms Regi Thomas",
            "Course code: UCS/CO/73\nCourse Tutor: Ms Jesilla Malarvizhi",
            "Course code: UCS/CO/73",
            "Course code: UCS/CE/10\nCourse Tutor: Ms Mercy Evangeline"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courselist_y1);
        listview = findViewById(R.id.list );

        myAdapter adapter = new myAdapter(this, course, detail, pic);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),Coursepage_y1.class);
                intent.putExtra("name",course[i]);
                startActivity(intent);
            }
        });


    }

    class myAdapter extends ArrayAdapter<String> {
        Context context;
        String course[];
        String details[];
        int images[];

        myAdapter(Context c, String title[], String subtitle[], int image[]) {
            super(c, R.layout.rowy1, R.id.maintext, title);
            this.context = c;
            this.course = title;
            this.details = subtitle;
            this.images = image;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View row = layoutInflater.inflate(R.layout.rowy1, parent, false);
            ImageView imgs = row.findViewById(R.id.image);
            TextView title = row.findViewById(R.id.maintext);
            TextView sub = row.findViewById(R.id.subtext);

            imgs.setImageResource(images[position]);
            title.setText(course[position]);
            sub.setText(details[position]);
            return row;

        }
    }
}


