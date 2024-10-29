package com.osedhelu.bnc.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class CarouselItem(
    val id: Int,
    @DrawableRes val imageResId: Int,
    @StringRes val contentDescriptionResId: Int
)

//@Composable
//fun CarouseCustom() {
//    val items =
//        listOf(
//            CarouselItem(0, R.drawable.carousel_image_1, R.string.carousel_image_1_description),
//            CarouselItem(1, R.drawable.carousel_image_2, R.string.carousel_image_2_description),
//            CarouselItem(2, R.drawable.carousel_image_3, R.string.carousel_image_3_description),
//            CarouselItem(3, R.drawable.carousel_image_4, R.string.carousel_image_4_description),
//            CarouselItem(4, R.drawable.carousel_image_5, R.string.carousel_image_5_description),
//        )
//    HorizontalMultiBrowseCarousel(
//        state = rememberCarouselState { items.count() },
//        modifier = Modifier.width(412.dp).height(221.dp),
//        preferredItemWidth = 186.dp,
//        itemSpacing = 8.dp,
//        contentPadding = PaddingValues(horizontal = 16.dp)
//    ) {
//            i ->
//        val item = items[i]
//        Image(
//            modifier = Modifier.height(205.dp).maskClip(MaterialTheme.shapes.extraLarge),
//            painter = painterResource(id = item.imageResId),
//            contentDescription = stringResource(item.contentDescriptionResId),
//            contentScale = ContentScale.Crop
//        )
//    }
//}
//
//
