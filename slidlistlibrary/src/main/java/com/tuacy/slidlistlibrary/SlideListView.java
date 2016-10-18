package com.tuacy.slidlistlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ListView;

public class SlideListView extends ListView {

	private Context     mContext;
	private long        mAnimationTime;
	private SlideMode   mSlideMode;
	private SlideAction mSlideLeftAction;
	private SlideAction mSlideRightAction;

	public SlideListView(Context context) {
		this(context, null);
	}

	public SlideListView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SlideListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mContext = context;
		init(attrs);
	}

	private void init(AttributeSet attrs) {
		if (attrs != null) {
			TypedArray styled = getContext().obtainStyledAttributes(attrs, R.styleable.SlideListView);
			mAnimationTime = styled.getInteger(R.styleable.SlideListView_slideAnimationTime, 0);
			mSlideMode = SlideMode.mapIntToValue(styled.getInteger(R.styleable.SlideListView_slideMode, 0));
			mSlideLeftAction = SlideAction.mapIntToValue(styled.getInteger(R.styleable.SlideListView_slideLeftAction, 0));
			mSlideRightAction = SlideAction.mapIntToValue(styled.getInteger(R.styleable.SlideListView_slideRightAction, 0));
			styled.recycle();
		}
	}


	public enum SlideMode {
		NONE(0x0),
		LEFT(0x1),
		RIGHT(0x2),
		BOTH(0x3);

		/**
		 * Maps an int to a specific mode. This is needed when saving state, or
		 * inflating the view from XML where the mode is given through a attr
		 * int.
		 *
		 * @param modeInt - int to map a Mode to
		 * @return Mode that modeInt maps to, or PULL_FROM_START by default.
		 */
		static SlideMode mapIntToValue(final int modeInt) {
			for (SlideMode value : SlideMode.values()) {
				if (modeInt == value.getIntValue()) {
					return value;
				}
			}
			// If not, return default
			return getDefault();
		}

		static SlideMode getDefault() {
			return NONE;
		}

		private int mIntValue;

		// The modeInt values need to match those from attrs.xml
		SlideMode(int modeInt) {
			mIntValue = modeInt;
		}

		int getIntValue() {
			return mIntValue;
		}

	}

	public enum SlideAction {
		SCROLL(0x0),
		REVEAL(0x1);

		/**
		 * Maps an int to a specific mode. This is needed when saving state, or
		 * inflating the view from XML where the mode is given through a attr
		 * int.
		 *
		 * @param actionInt - int to map a Mode to
		 * @return Mode that modeInt maps to, or PULL_FROM_START by default.
		 */
		static SlideAction mapIntToValue(final int actionInt) {
			for (SlideAction value : SlideAction.values()) {
				if (actionInt == value.getIntValue()) {
					return value;
				}
			}

			// If not, return default
			return getDefault();
		}

		static SlideAction getDefault() {
			return SCROLL;
		}

		private int mIntValue;

		// The modeInt values need to match those from attrs.xml
		SlideAction(int actionInt) {
			mIntValue = actionInt;
		}

		int getIntValue() {
			return mIntValue;
		}

	}
}
