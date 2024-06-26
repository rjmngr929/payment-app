package com.example.paymentapplication.ui.onBoarding.entity

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.paymentapplication.R

enum class OnBoardingPage(
    @StringRes val titleResource: Int,
    @StringRes val subTitleResource: Int,
    @StringRes val descriptionResource: Int,
    @DrawableRes val logoResource: Int
) {

    ONE(R.string.onboarding_slide1_title, R.string.onboarding_slide1_subtitle,R.string.onboarding_slide1_desc, R.drawable.onboard_1_img),
    TWO(R.string.onboarding_slide2_title, R.string.onboarding_slide2_subtitle,R.string.onboarding_slide2_desc, R.drawable.onboard_2_img),
    THREE(R.string.onboarding_slide2_title, R.string.onboarding_slide3_subtitle,R.string.onboarding_slide1_desc, R.drawable.onboard_3_img)

}