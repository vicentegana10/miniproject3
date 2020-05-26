package Clases

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemGif(var title: String, var url: String): Parcelable

@Parcelize
data class RandomGif(var data :Gif): Parcelable

@Parcelize
data class SearchCategories(var data :List<Category>): Parcelable

@Parcelize
data class SearchGif(var data :List<Gif>): Parcelable

@Parcelize
data class Category(var name: String, var subcategories: List<SubCategory>): Parcelable

@Parcelize
data class SubCategory(val name: String, val name_encoded: String): Parcelable