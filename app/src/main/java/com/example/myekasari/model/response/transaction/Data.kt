package com.example.myekasari.model.response.transaction


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    @Expose
    @SerializedName("created_at")
    val createdAt: String?,
    @Expose
    @SerializedName("deleted_at")
    val deletedAt: String?,
    @Expose
    @SerializedName("food")
    val food: Food?,
    @Expose
    @SerializedName("food_id")
    val foodId: String?,
    @Expose
    @SerializedName("id")
    val id: Int?,
    @Expose
    @SerializedName("payment_url")
    val paymentUrl: String?,
    @Expose
    @SerializedName("quantity")
    val quantity: String?,
    @Expose
    @SerializedName("status")
    val status: String?,
    @Expose
    @SerializedName("total")
    val total: String?,
    @Expose
    @SerializedName("updated_at")
    val updatedAt: String?,
    @Expose
    @SerializedName("user")
    val user: User?,
    @Expose
    @SerializedName("user_id")
    val userId: String?
) : Parcelable