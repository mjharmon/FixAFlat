package com.pivotalcog.fixaflat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FixAFlat extends AppCompatActivity {
	protected int currentStep = -1;
	protected Boolean skipOnSchrader = false;
	protected resourcePackage resources = new resourcePackage(this);

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_aflat);
		displayStep(true);
	}

	@Override
	public void onBackPressed() {
		if (this.currentStep <= 0) {
			super.onBackPressed();
		}
		else {
			this.displayStep(false);
		}
	}

	private View.OnClickListener nextBackButtonListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
		Boolean direction;
		switch(v.getId()) {
			case R.id.leftButton:
				direction = false;
				break;
			default:
				direction = true;
				break;
		}
		displayStep(direction);
		}
	};

	private View.OnClickListener schraderValveButtonListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch(v.getId()) {
				case R.id.leftButton:
					skipOnSchrader = false;
					break;
				case R.id.rightButton:
					skipOnSchrader = true;
					break;
			}
			displayStep(true);
		}
	};

	private View.OnClickListener prestaValveButtonListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
		Boolean direction;
		switch(v.getId()) {
			case R.id.leftButton:
				direction = false;
				break;
			default:
				resources.prestaTrue();
				direction = true;
				break;
		}
		displayStep(direction);
		}
	};

	protected void displayStep(Boolean direction) {
		if (direction) {
			if ((this.currentStep == 0) && (this.skipOnSchrader)) {
				this.currentStep++;
			}
			this.currentStep++;
		}
		else {
			if ((this.currentStep == 2) && (this.skipOnSchrader)) {
				this.currentStep--;
				this.skipOnSchrader = false;
			}
			this.currentStep--;
		}

		if ((this.currentStep >= 0) && (this.currentStep < 7)) {
			this.displayStep(this.currentStep);
		}
	}

	private void displayStep(int stepNumber) {
		Button backButton;
		Button nextButton;
		ImageView displayImage;
		TextView displayTextView;
		resourcePackage displayResources = this.resources.getPackage(stepNumber);
		View.OnClickListener selectedButtonListener;


		switch(stepNumber) {
			default:
				selectedButtonListener = this.nextBackButtonListener;
				break;
			case 0:
				selectedButtonListener = this.schraderValveButtonListener;
				break;
			case 1:
				selectedButtonListener = this.prestaValveButtonListener;
				break;
		}

		displayImage = (ImageView) findViewById(R.id.displayImage);
		backButton = (Button) findViewById(R.id.leftButton);
		nextButton = (Button) findViewById(R.id.rightButton);
		displayTextView = (TextView) findViewById(R.id.displayText);

		if (nextButton != null) {
			if (displayResources.rightButtonText != null) {
				nextButton.setText(displayResources.rightButtonText);
				nextButton.setOnClickListener(selectedButtonListener);
				nextButton.setVisibility(View.VISIBLE);
			}
			else {
				nextButton.setVisibility(View.GONE);
			}
		}

		if (backButton != null) {
			if (displayResources.leftButtonText != null) {
				backButton.setText(displayResources.leftButtonText);
				backButton.setOnClickListener(selectedButtonListener);
				backButton.setVisibility(View.VISIBLE);
			}
			else {
				backButton.setVisibility(View.GONE);
			}
		}

		if (displayImage != null) {
			if (displayResources.image != null) {
				displayImage.setImageDrawable(displayResources.image);
				displayImage.setVisibility(View.VISIBLE);
			}
			else {
				displayImage.setVisibility(View.GONE);
			}
		}

		if (displayTextView != null) {
			if (displayResources.displayText != null) {
				displayTextView.setText(displayResources.displayText);
				displayTextView.setVisibility(View.VISIBLE);
			}
			else {
				displayTextView.setVisibility(View.GONE);
			}
		}
	}
}

