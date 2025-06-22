package com.example.reframe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.reframe.api.ReviewResponse
import com.example.reframe.api.StoreResponse
import com.example.reframe.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeStoreAdapter(
    private val onItemClick: (StoreResponse) -> Unit
) : ListAdapter<StoreResponse, HomeStoreAdapter.StoreViewHolder>(DiffCallback()) {

    inner class StoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgStore: ImageView = itemView.findViewById(R.id.imgStore)
        private val tvStoreName: TextView = itemView.findViewById(R.id.tvStoreName)
        private val ivStar: ImageView = itemView.findViewById(R.id.ivStar)
        private val tvRating: TextView = itemView.findViewById(R.id.tvRating)
        private val tvReviewCount: TextView = itemView.findViewById(R.id.tvReviewCount)
        private val ivHeart: ImageView = itemView.findViewById(R.id.ivHeart)

        private var isLiked = false

        fun bind(store: StoreResponse) {
            // 👉 1️⃣ 이미지 (더미)
            imgStore.setImageResource(store.imageResId ?: R.drawable.receipt_image)

            // 👉 2️⃣ 이름 (7글자 제한은 XML에서 ellipsize)
            tvStoreName.text = store.name

            // 👉 3️⃣ 초기 별점 / 리뷰수
            ivStar.setImageResource(R.drawable.ic_starr)
            tvRating.text = "0.0"
            tvReviewCount.text = "30" // 임시 하드코딩

            // 👉 4️⃣ 별점 API 호출
            RetrofitClient.instance.getStoreReviews(store.id ?: 0)
                .enqueue(object : Callback<List<ReviewResponse>> {
                    override fun onResponse(
                        call: Call<List<ReviewResponse>>,
                        response: Response<List<ReviewResponse>>
                    ) {
                        if (response.isSuccessful) {
                            val reviews = response.body() ?: emptyList()
                            val avgRating = if (reviews.isNotEmpty()) {
                                reviews.map { it.rating }.average().toFloat()
                            } else 0.0f
                            tvRating.text = String.format("%.1f", avgRating)
                        }
                    }

                    override fun onFailure(call: Call<List<ReviewResponse>>, t: Throwable) {
                        t.printStackTrace()
                    }
                })

            // 👉 5️⃣ 하트 상태 : store.isLiked 로 반영
            ivHeart.setImageResource(
                if (store.isLiked) R.drawable.ic_heartfulll else R.drawable.ic_heartt
            )

            // 👉 6️⃣ 전체 카드 클릭 (하트 눌렀으면 무시)
            itemView.setOnClickListener {
                if (ivHeart.isPressed) {
                    android.util.Log.d("HomeStoreAdapter", "👉 [LOG] 하트 누르는 중이라 카드 클릭 무시됨")
                    return@setOnClickListener
                }
                android.util.Log.d("HomeStoreAdapter", "👉 [LOG] 카드 클릭됨 → ${store.name}")
                onItemClick(store)
            }

            // 👉 7️⃣ 하트 클릭 (addLove 호출 + 로그) — Void 버전!
            ivHeart.setOnClickListener {
                ivHeart.isPressed = true

                store.isLiked = !store.isLiked

                android.util.Log.d("HomeStoreAdapter", "👉 [LOG] 하트 클릭됨, 새로운 상태 = ${store.isLiked}")

                if (store.isLiked) {
                    RetrofitClient.instance.addLove(7L, store.id ?: 0)
                        .enqueue(object : Callback<Void> {
                            override fun onResponse(
                                call: Call<Void>,
                                response: Response<Void>
                            ) {
                                ivHeart.setImageResource(R.drawable.ic_heartfulll)
                                android.util.Log.d(
                                    "HomeStoreAdapter",
                                    "✅ [LOG] addLove 성공 (Void)"
                                )
                                ivHeart.isPressed = false
                            }

                            override fun onFailure(call: Call<Void>, t: Throwable) {
                                t.printStackTrace()
                                store.isLiked = false
                                ivHeart.setImageResource(R.drawable.ic_heartt)
                                android.util.Log.e(
                                    "HomeStoreAdapter",
                                    "❌ [LOG] addLove 실패: ${t.message}"
                                )
                                ivHeart.isPressed = false
                            }
                        })
                } else {
                    ivHeart.setImageResource(R.drawable.ic_heartt)
                    android.util.Log.d("HomeStoreAdapter", "✅ [LOG] 하트 해제됨 (API 없음, UI만 변경)")
                    ivHeart.isPressed = false
                }
            }
        }





    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home_store, parent, false)
        return StoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<StoreResponse>() {
        override fun areItemsTheSame(oldItem: StoreResponse, newItem: StoreResponse): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: StoreResponse, newItem: StoreResponse): Boolean {
            return oldItem == newItem
        }
    }
}
