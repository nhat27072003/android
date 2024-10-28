package com.example.email

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

data class EmailItem(val sender: String, val subject: String, val preview: String, val time: String)

class EmailAdapter(private val emailList: List<EmailItem>) : RecyclerView.Adapter<EmailAdapter.EmailViewHolder>() {

    class EmailViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatarImageView: ImageView = view.findViewById(R.id.avatarImageView)
        val senderTextView: TextView = view.findViewById(R.id.senderTextView)
        val subjectTextView: TextView = view.findViewById(R.id.subjectTextView)
        val previewTextView: TextView = view.findViewById(R.id.previewTextView)
        val timeTextView: TextView = view.findViewById(R.id.timeTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return EmailViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        val email = emailList[position]
        holder.senderTextView.text = email.sender
        holder.subjectTextView.text = email.subject
        holder.previewTextView.text = email.preview
        holder.timeTextView.text = email.time

        val firstLetter = email.sender.first().toString()
        holder.avatarImageView.setImageDrawable(createAvatarDrawable(holder.itemView.context, firstLetter))
    }


    fun getRandomColor(): Int {
        val random = Random.Default
        return Color.rgb(
            random.nextInt(256),
            random.nextInt(256),
            random.nextInt(256)
        )
    }
    private fun createAvatarDrawable(context: Context, letter: String): Drawable {
        val size = 80 // Kích thước avatar
        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint()

        // Tạo hình tròn
        paint.color = getRandomColor()
        paint.isAntiAlias = true
        canvas.drawCircle(size / 2f, size / 2f, size / 2f, paint)

        // Thêm chữ cái đầu
        paint.color = Color.WHITE
        paint.textSize = 30f
        paint.textAlign = Paint.Align.CENTER
        val yPos = (canvas.height / 2 - (paint.descent() + paint.ascent()) / 2)
        canvas.drawText(letter, canvas.width / 2f, yPos, paint)
        return BitmapDrawable(context.resources, bitmap)
    }
    override fun getItemCount() = emailList.size
}

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var emailAdapter: EmailAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)



        val emailList = listOf(
            EmailItem(
                "Alice",
                "Meeting tomorrow",
                "Hi, just a reminder about...",
                "09:00 AM"
            ),
            EmailItem(
                "Bob",
                "Project Update",
                "Here's the latest on the project...",
                "10:15 AM"
            ),
            EmailItem(
                "Charlie",
                "Lunch Plans",
                "Are we still on for lunch?",
                "11:30 AM"
            ),
            EmailItem(
                "Diana",
                "Vacation Photos",
                "Check out the photos from my trip!",
                "01:00 PM"
            ),
            EmailItem(
                "Eve",
                "Birthday Invitation",
                "You're invited to my birthday party!",
                "02:45 PM"
            ),
            EmailItem(
                "Frank",
                "Code Review",
                "Could you review my code?",
                "03:30 PM"
            ),
            EmailItem(
                "Grace",
                "Workshop Details",
                "Here are the details for the workshop.",
                "04:15 PM"
            ),
            EmailItem(
                "Hank",
                "Invoice",
                "Please find attached invoice for last month.",
                "05:00 PM"
            ),
            EmailItem(
                "Ivy",
                "Concert Tickets",
                "I got us tickets to the concert!",
                "06:45 PM"
            ),
            EmailItem(
                "Jack",
                "Weekly Report",
                "Here’s the weekly status report.",
                "08:30 PM"
            )
        )

        emailAdapter = EmailAdapter(emailList)
        recyclerView.adapter = emailAdapter
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}