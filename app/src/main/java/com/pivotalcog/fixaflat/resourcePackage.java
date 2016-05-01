package com.pivotalcog.fixaflat;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;

/**
 * Created by mjharmon on 4/27/16.
 */
public class resourcePackage {
	public String leftButtonText;
	public String rightButtonText;
	public Drawable image;
	public String displayText;
	static private Context theContext;
	static private Boolean presta = false;

	public resourcePackage(Context appContext) {
		this.initPackage();
		resourcePackage.theContext = appContext;
	}

	public void prestaTrue() {
		this.presta = true;
	}

	private void initPackage() {
		this.leftButtonText = null;
		this.rightButtonText = null;
		this.image = null;
		this.displayText = null;
	}

	public void getPackage(int stepNumber) {
		this.initPackage();
		switch (stepNumber) {
			case 0: /* is this a scrader valve?  */
				this.displayText = resourcePackage.theContext.getResources().getString(R.string.schraderText);
				this.image = ResourcesCompat.getDrawable(resourcePackage.theContext.getResources(), R.drawable.valveschrader, null);
				this.leftButtonText = resourcePackage.theContext.getResources().getString(R.string.notThisValveButtonText);
				this.rightButtonText = resourcePackage.theContext.getResources().getString(R.string.schraderButtonText);
				break;

			case 1: /* is this a presta valve?  */
				this.displayText = resourcePackage.theContext.getResources().getString(R.string.prestaText);
				this.image = ResourcesCompat.getDrawable(this.theContext.getResources(), R.drawable.valvepresta, null);
				this.leftButtonText = resourcePackage.theContext.getResources().getString(R.string.back);
				this.rightButtonText = resourcePackage.theContext.getResources().getString(R.string.prestaButtonText);
				break;


			case 2: /* partially remove tire */
				this.rightButtonText = resourcePackage.theContext.getResources().getString(R.string.next);
				this.leftButtonText = resourcePackage.theContext.getResources().getString(R.string.back);
				if (this.presta) {
					this.displayText = resourcePackage.theContext.getString(R.string.prestaTireRemoveStart);
				} else {
					this.displayText = resourcePackage.theContext.getString(R.string.schraderTireRemoveStart);
				}

				this.displayText += resourcePackage.theContext.getResources().getString(R.string.tireRemoveStart);

				break;
			case 3: /* remove tube */
				this.rightButtonText = resourcePackage.theContext.getResources().getString(R.string.next);
				this.leftButtonText = resourcePackage.theContext.getResources().getString(R.string.back);

				if (this.presta) {
					this.displayText = resourcePackage.theContext.getResources().getString(R.string.prestaTubeRemove);
				} else {
					this.displayText = resourcePackage.theContext.getResources().getString(R.string.schraderTubeRemove);
				}

				break;
			case 4: /* finish removing tire */
				this.rightButtonText = resourcePackage.theContext.getResources().getString(R.string.next);
				this.leftButtonText = resourcePackage.theContext.getResources().getString(R.string.back);
				this.displayText = resourcePackage.theContext.getResources().getString(R.string.tireRemoveFinish);
				break;
			case 5: /* replace tube & tire */
				this.rightButtonText = resourcePackage.theContext.getResources().getString(R.string.next);
				this.leftButtonText = resourcePackage.theContext.getResources().getString(R.string.back);
				if (this.presta) {
					this.displayText = resourcePackage.theContext.getResources().getString(R.string.prestaTubeReplace);
				} else {
					this.displayText = resourcePackage.theContext.getResources().getString(R.string.schraderTubeReplace);
				}

				this.displayText += resourcePackage.theContext.getResources().getString(R.string.finishTubeTireReplace);
				break;

			case 6: /* inflate tire */
//				this.rightButtonText = resourcePackage.theContext.getResources().getString(R.string.next);
				this.leftButtonText = resourcePackage.theContext.getResources().getString(R.string.back);

				if (this.presta) {
					this.displayText = resourcePackage.theContext.getResources().getString(R.string.prestaInflateTire);
				} else {
					this.displayText = resourcePackage.theContext.getResources().getString(R.string.schraderInflateTire);
				}
				break;
		}
	}
}
