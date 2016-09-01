package com.zhycheng.viewflipper;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class Welcome extends Activity implements OnGestureListener {
	private GestureDetector detector;
	private ViewFlipper flipper;
	private Button button1;
	private Button button2;
	int i = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		detector = new GestureDetector(this);
		flipper = (ViewFlipper) this.findViewById(R.id.ViewFlipper1);
		flipper.addView(addImageView(R.drawable.png1o));
		flipper.addView(addView());
		flipper.startFlipping();
	}

	private View addImageView(int id) {
		ImageView iv = new ImageView(this);
		iv.setImageResource(id);
		return iv;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return this.detector.onTouchEvent(event);
	}

	private View addView() {
		LayoutInflater inflater = LayoutInflater.from(this);
		View view = inflater.inflate(R.layout.page2, null);
		button1 = (Button) view.findViewById(R.id.button1);
		button1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(Welcome.this, "Clicked1", Toast.LENGTH_SHORT).show();
			}
		});
		button2 = (Button) view.findViewById(R.id.button2);
		button2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(Welcome.this, "Clicked2", Toast.LENGTH_SHORT).show();
			}
		});
		return view;
	}

	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		System.out.println("in------------>>>>>>>");
		if (e1.getX() - e2.getX() > 120) {
			if (i < 3) {
				i++;
				this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,
						R.anim.animation_right_in));
				this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
						R.anim.animation_left_out));
				this.flipper.showNext();
			}
			return true;
		} else if (e1.getX() - e2.getX() < -120) {
			if (i > 0) {
				i--;
				this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,
						R.anim.animation_left_in));
				this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this,
						R.anim.animation_right_out));
				this.flipper.showPrevious();
			}
			return true;
		}
		return false;

	}
}