package com.example.th01

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var edtHoTen: EditText
    private lateinit var edtTuoi: EditText
    private lateinit var btnKiemTra: Button
    private lateinit var tvKetQua: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ các view
        edtHoTen = findViewById(R.id.edtHoTen)
        edtTuoi = findViewById(R.id.edtTuoi)
        btnKiemTra = findViewById(R.id.btnKiemTra)
        tvKetQua = findViewById(R.id.tvKetQua)

        // Xử lý sự kiện khi nhấn nút Kiểm tra
        btnKiemTra.setOnClickListener {
            kiemTraTuoi()
        }
    }

    private fun kiemTraTuoi() {
        val hoTen = edtHoTen.text.toString().trim()
        val tuoiStr = edtTuoi.text.toString().trim()

        // Kiểm tra dữ liệu nhập
        if (hoTen.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập họ tên", Toast.LENGTH_SHORT).show()
            return
        }

        if (tuoiStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tuổi", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val tuoi = tuoiStr.toInt()

            // Phân loại độ tuổi
            val phanLoai = when {
                tuoi > 65 -> "Người già"
                tuoi in 6..65 -> "Người lớn"
                tuoi in 2..5 -> "Trẻ em"
                else -> "Em bé"
            }

            // Hiển thị kết quả
            tvKetQua.text = "Kết quả: $phanLoai"

        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Tuổi phải là số hợp lệ", Toast.LENGTH_SHORT).show()
        }
    }
}