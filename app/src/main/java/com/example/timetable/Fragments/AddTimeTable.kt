package com.example.timetable.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.timetable.Models.MyObject
import com.example.timetable.Models.TimeTable
import com.example.timetable.Database.AppDataBase
import com.example.timetable.R
import com.example.timetable.databinding.FragmentAddTimeTableBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class AddTimeTable : Fragment() {
    lateinit var binding: FragmentAddTimeTableBinding
    lateinit var appDataBase: AppDataBase
    lateinit var list: ArrayList<TimeTable>
    var year = ""
    var month = ""
    var day = ""
    var hour = 0
    var minute = 0
    var hour2 = 0
    var minute2 = 0
    var hourString = "00"
    var minuteString = "00"
    var hour2String = "00"
    var minute2String = "00"

    @SuppressLint("NewApi", "SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTimeTableBinding.inflate(layoutInflater)
        val listImage = ArrayList<Int>()
        listImage.add(R.drawable.image11)
        listImage.add(R.drawable.image12)
        listImage.add(R.drawable.image13)
        listImage.add(R.drawable.image14)
        listImage.add(R.drawable.image15)
        listImage.add(R.drawable.image16)
        listImage.add(R.drawable.image17)
        listImage.add(R.drawable.image18)
        listImage.add(R.drawable.image19)
        listImage.add(R.drawable.image20)
        val random = Random().nextInt(listImage.size)
        binding.imageAddTimeTab.setImageResource(listImage[random])

        appDataBase = AppDataBase.getInstance(binding.root.context)

        list = appDataBase.biletDuo().getAllTimeTable() as ArrayList
        when (MyObject.isAdd) {
            true -> {

                binding.apply {
                    val list2 = ArrayList<String>()
                    list2.add("House")
                    list2.add("Sport")
                    list2.add("Study")
                    list2.add("Others")
                    val adapter =
                        ArrayAdapter(
                            binding.root.context,
                            android.R.layout.simple_list_item_activated_1,
                            list2
                        )

                    spinner.adapter = adapter

                    plusMinusHours()
                    plusMinusMinute()
                    plusMinusHoursFrom()
                    plusMinusMinuteFrom()
                    btnSave.setOnClickListener {
                        val current = LocalDateTime.now()
                        val formatter = DateTimeFormatter.ofPattern("yy-MM-dd")
                        val formatted = current.format(formatter)
                        var timeTable = TimeTable()
                        year = edtYear.text.toString()
                        month = edtMonth.text.toString()
                        day = edtDay.text.toString()

                        var hours = ""
                        var minut = ""

                        var hours2 = ""
                        var minutes2 = ""

                        hours = if (hourString.substring(0, 1) == "0") {
                            hourString.substring(1, hourString.length)
                        } else {
                            hourString.substring(0, hourString.length)
                        }
                        minut = minuteString.substring(0, minuteString.length)

                        hours2 = if (hour2String.substring(0, 1) == "0") {
                            hour2String.substring(1, hour2String.length)
                        } else {
                            hour2String.substring(0, hour2String.length)
                        }

                        minutes2 = minute2String.substring(0, minute2String.length)

                        val time = "$hours$minut"
                        val time2 = "$hours2$minutes2"
                        val timeSave = "$hourString:$minuteString"
                        val timeSave2 = "$hour2String:$minute2String"

                        if (time != time2) {
                            if (time.toInt() < time2.toInt() && year.isNotEmpty()
                                && month.isNotEmpty() && day.isNotEmpty()
                            ) {
                                if (year.length == 4 && month.length <= 2 && day.length <= 2
                                    && month != "0" && month != "00" && year != "0" && year != "00"
                                    && year != "000" && year != "0000" && day != "0" && day != "00"
                                ) {
                                    if (month.length == 1) {
                                        month = "0$month"
                                    }
                                    if (day.length == 1) {
                                        day = "0$day"
                                    }
                                    var yearDouble = year.toDouble() / 4
                                    if (yearDouble.toString().substring(
                                            yearDouble.toString().indexOf('.') + 1,
                                            yearDouble.toString().length
                                        ) == "0" && month == "02" && day.toInt() <= 29
                                    ) {
                                        timeTable = TimeTable(
                                            date = "$year-$month-$day",
                                            fromTime = timeSave,
                                            untilTime = timeSave2,
                                            type = spinner.selectedItemPosition.toString()
                                        )
                                        appDataBase.biletDuo().addTimeTable(timeTable)
                                        list.add(timeTable)
                                        findNavController().popBackStack()
                                        Toast.makeText(
                                            context,
                                            "Save Time $time $time2",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    } else if (month == "01" && day.toInt() <= 31) {
                                        timeTable = TimeTable(
                                            date = "$year-$month-$day",
                                            fromTime = timeSave,
                                            untilTime = timeSave2,
                                            type = spinner.selectedItemPosition.toString()
                                        )
                                        appDataBase.biletDuo().addTimeTable(timeTable)
                                        list.add(timeTable)
                                        findNavController().popBackStack()
                                        Toast.makeText(
                                            context,
                                            "Save Time $time $time2",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    } else if (month == "02" && day.toInt() <= 28) {
                                        timeTable = TimeTable(
                                            date = "$year-$month-$day",
                                            fromTime = timeSave,
                                            untilTime = timeSave2,
                                            type = spinner.selectedItemPosition.toString()
                                        )
                                        appDataBase.biletDuo().addTimeTable(timeTable)
                                        list.add(timeTable)
                                        findNavController().popBackStack()
                                        Toast.makeText(
                                            context,
                                            "Save Time $time $time2",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    } else if (month == "03" && day.toInt() <= 31) {
                                        timeTable = TimeTable(
                                            date = "$year-$month-$day",
                                            fromTime = timeSave,
                                            untilTime = timeSave2,
                                            type = spinner.selectedItemPosition.toString()
                                        )
                                        appDataBase.biletDuo().addTimeTable(timeTable)
                                        list.add(timeTable)
                                        findNavController().popBackStack()
                                        Toast.makeText(
                                            context,
                                            "Save Time $time $time2",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    } else if (month == "04" && day.toInt() <= 30) {
                                        timeTable = TimeTable(
                                            date = "$year-$month-$day",
                                            fromTime = timeSave,
                                            untilTime = timeSave2,
                                            type = spinner.selectedItemPosition.toString()
                                        )
                                        appDataBase.biletDuo().addTimeTable(timeTable)
                                        list.add(timeTable)
                                        findNavController().popBackStack()
                                        Toast.makeText(
                                            context,
                                            "Save Time $time $time2",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    } else if (month == "05" && day.toInt() <= 31) {
                                        timeTable = TimeTable(
                                            date = "$year-$month-$day",
                                            fromTime = timeSave,
                                            untilTime = timeSave2,
                                            type = spinner.selectedItemPosition.toString()
                                        )
                                        appDataBase.biletDuo().addTimeTable(timeTable)
                                        list.add(timeTable)
                                        findNavController().popBackStack()
                                        Toast.makeText(
                                            context,
                                            "Save Time $time $time2",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    } else if (month == "06" && day.toInt() <= 30) {
                                        timeTable = TimeTable(
                                            date = "$year-$month-$day",
                                            fromTime = timeSave,
                                            untilTime = timeSave2,
                                            type = spinner.selectedItemPosition.toString()
                                        )
                                        appDataBase.biletDuo().addTimeTable(timeTable)
                                        list.add(timeTable)
                                        findNavController().popBackStack()
                                        Toast.makeText(
                                            context,
                                            "Save Time $time $time2",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    } else if (month == "07" && day.toInt() <= 31) {
                                        timeTable = TimeTable(
                                            date = "$year-$month-$day",
                                            fromTime = timeSave,
                                            untilTime = timeSave2,
                                            type = spinner.selectedItemPosition.toString()
                                        )
                                        appDataBase.biletDuo().addTimeTable(timeTable)
                                        list.add(timeTable)
                                        findNavController().popBackStack()
                                        Toast.makeText(
                                            context,
                                            "Save Time $time $time2",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    } else if (month == "08" && day.toInt() <= 31) {
                                        timeTable = TimeTable(
                                            date = "$year-$month-$day",
                                            fromTime = timeSave,
                                            untilTime = timeSave2,
                                            type = spinner.selectedItemPosition.toString()
                                        )
                                        appDataBase.biletDuo().addTimeTable(timeTable)
                                        list.add(timeTable)
                                        findNavController().popBackStack()
                                        Toast.makeText(
                                            context,
                                            "Save Time $time $time2",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    } else if (month == "09" && day.toInt() <= 30) {
                                        timeTable = TimeTable(
                                            date = "$year-$month-$day",
                                            fromTime = timeSave,
                                            untilTime = timeSave2,
                                            type = spinner.selectedItemPosition.toString()
                                        )
                                        appDataBase.biletDuo().addTimeTable(timeTable)
                                        list.add(timeTable)
                                        findNavController().popBackStack()
                                        Toast.makeText(
                                            context,
                                            "Save Time $time $time2",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    } else if (month == "10" && day.toInt() <= 31) {
                                        timeTable = TimeTable(
                                            date = "$year-$month-$day",
                                            fromTime = timeSave,
                                            untilTime = timeSave2,
                                            type = spinner.selectedItemPosition.toString()
                                        )
                                        appDataBase.biletDuo().addTimeTable(timeTable)
                                        list.add(timeTable)
                                        findNavController().popBackStack()
                                        Toast.makeText(
                                            context,
                                            "Save Time $time $time2",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    } else if (month == "11" && day.toInt() <= 30) {
                                        timeTable = TimeTable(
                                            date = "$year-$month-$day",
                                            fromTime = timeSave,
                                            untilTime = timeSave2,
                                            type = spinner.selectedItemPosition.toString()
                                        )
                                        appDataBase.biletDuo().addTimeTable(timeTable)
                                        list.add(timeTable)
                                        findNavController().popBackStack()
                                        Toast.makeText(
                                            context,
                                            "Save Time $time $time2",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    } else if (month == "12" && day.toInt() <= 31) {
                                        timeTable = TimeTable(
                                            date = "$year-$month-$day",
                                            fromTime = timeSave,
                                            untilTime = timeSave2,
                                            type = spinner.selectedItemPosition.toString()
                                        )
                                        appDataBase.biletDuo().addTimeTable(timeTable)
                                        list.add(timeTable)
                                        findNavController().popBackStack()
                                        Toast.makeText(
                                            context,
                                            "Save Time $time $time2",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    } else {
                                        Toast.makeText(context, "no such month", Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                } else {
                                    Toast.makeText(context, "editText is wrong", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            } else {
                                Toast.makeText(
                                    context,
                                    "from smaller than until or editText is Empty",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                                Toast.makeText(
                                    context,
                                    "Save Time $time $time2",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        } else {
                            Toast.makeText(
                                context,
                                "From Time equals Until Time",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
                }
            }
            false -> {
                binding.apply {
                    val froms = MyObject.timeTable.fromTime
                    val untils = MyObject.timeTable.untilTime


                    hourString = froms.toString().substring(0, froms.toString().indexOf(':'))
                    tvHoursFrom.text = hourString

                    if (hourString.substring(0,1) == "0"){
                        hour = hourString.substring(1,2).toInt()
                    }else{
                        hour = hourString.toInt()
                    }

                    minuteString = froms.toString()
                        .substring(froms.toString().indexOf(':') + 1, froms.toString().length)
                    tvMinuteFrom.text = minuteString

                    if (minuteString == "00"){
                        minute = minuteString.substring(1,2).toInt()
                    }else{
                        minute = minuteString.toInt()
                    }

                    hour2String = untils.toString().substring(0, untils.toString().indexOf(':'))
                    tvHours.text = hour2String

                    if (hour2String.substring(0,1) == "0"){
                        hour2 = hour2String.substring(1,2).toInt()
                    }else{
                        hour2 = hour2String.toInt()
                    }

                    minute2String = untils.toString()
                        .substring(untils.toString().indexOf(':') + 1, untils.toString().length)
                    tvMinute.text = minute2String

                    if (minute2String == "00"){
                        minute2 = minute2String.substring(1,2).toInt()
                    }else{
                        minute2 = minute2String.toInt()
                    }

                    edtYear.setText(MyObject.timeTable.date.toString().substring(0, 4))
                    edtMonth.setText(MyObject.timeTable.date.toString().substring(5, 7))
                    edtDay.setText(
                        MyObject.timeTable.date.toString()
                            .substring(8, MyObject.timeTable.date.toString().length)

                    )

                    Toast.makeText(context, "hour:$hour,minute:$minute", Toast.LENGTH_SHORT).show()
                    Toast.makeText(context, "hour:$hour2,minute:$minute2", Toast.LENGTH_SHORT).show()
                    val list2 = ArrayList<String>()
                    list2.add("House")
                    list2.add("Sport")
                    list2.add("Study")
                    list2.add("Others")
                    val adapter =
                        ArrayAdapter(
                            binding.root.context,
                            android.R.layout.simple_list_item_activated_1,
                            list2
                        )
                    spinner.adapter = adapter
                    spinner.setSelection(MyObject.timeTable.type.toString().toInt())

                    plusMinusHours()
                    plusMinusMinute()
                    plusMinusHoursFrom()
                    plusMinusMinuteFrom()
                    btnSave.setOnClickListener {
                        val current = LocalDateTime.now()
                        val formatter = DateTimeFormatter.ofPattern("yy-MM-dd")
                        val formatted = current.format(formatter)
                        year = edtYear.text.toString()
                        month = edtMonth.text.toString()
                        day = edtDay.text.toString()

                        var hours = ""
                        var minut = ""

                        var hours2 = ""
                        var minutes2 = ""

                        hours = if (hourString.substring(0, 1) == "0") {
                            hourString.substring(1, hourString.length)
                        } else {
                            hourString.substring(0, hourString.length)
                        }

                        minut = minuteString.substring(0, minuteString.length)

                        hours2 = if (hour2String.substring(0, 1) == "0") {
                            hour2String.substring(1, hour2String.length)
                        } else {
                            hour2String.substring(0, hour2String.length)
                        }

                        minutes2 = minute2String.substring(0, minute2String.length)

                        val time = "$hours$minut"
                        val time2 = "$hours2$minutes2"
                        val timeSave = "$hourString:$minuteString"
                        val timeSave2 = "$hour2String:$minute2String"

                        if (time != time2) {
                            if (time.toInt() < time2.toInt() && year.isNotEmpty()
                                && month.isNotEmpty() && day.isNotEmpty()
                            ) {
                                if (year.length == 4 && month.length <= 2 && day.length <= 2
                                    && month != "0" && month != "00" && year != "0" && year != "00"
                                    && year != "000" && year != "0000" && day != "0" && day != "00"
                                ) {
                                    if (month.length == 1) {
                                        month = "0$month"
                                    }
                                    if (day.length == 1) {
                                        day = "0$day"
                                    }
                                    var yearDouble = year.toDouble() / 4
                                    if (yearDouble.toString().substring(
                                            yearDouble.toString().indexOf('.') + 1,
                                            yearDouble.toString().length
                                        ) == "0" && month == "02" && day.toInt() <= 29
                                    ) {
                                        val timetable = MyObject.timeTable
                                            timetable.date = "$year-$month-$day"
                                            timetable.fromTime = timeSave
                                            timetable.untilTime = timeSave2
                                            timetable.type = spinner.selectedItemPosition.toString()

                                        appDataBase.biletDuo().updateTimeTable(timetable)
                                        findNavController().popBackStack()
                                        Toast.makeText(
                                            context,
                                            "Save Time $time $time2",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    } else if (month == "01" && day.toInt() <= 31) {
                                        val timetable = MyObject.timeTable
                                            timetable.date = "$year-$month-$day"
                                            timetable.fromTime = timeSave
                                            timetable.untilTime = timeSave2
                                            timetable.type = spinner.selectedItemPosition.toString()

                                        appDataBase.biletDuo().updateTimeTable(timetable)
                                        findNavController().popBackStack()
                                        Toast.makeText(
                                            context,
                                            "Save Time $time $time2",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    } else if (month == "02" && day.toInt() <= 28) {
                                        val timetable = MyObject.timeTable
                                            timetable.date = "$year-$month-$day"
                                            timetable.fromTime = timeSave
                                            timetable.untilTime = timeSave2
                                            timetable.type = spinner.selectedItemPosition.toString()

                                        appDataBase.biletDuo().updateTimeTable(timetable)
                                        findNavController().popBackStack()
                                        Toast.makeText(
                                            context,
                                            "Save Time $time $time2",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    } else if (month == "03" && day.toInt() <= 31) {
                                        val timetable = MyObject.timeTable
                                            timetable.date = "$year-$month-$day"
                                            timetable.fromTime = timeSave
                                            timetable.untilTime = timeSave2
                                            timetable.type = spinner.selectedItemPosition.toString()

                                        appDataBase.biletDuo().updateTimeTable(timetable)
                                        findNavController().popBackStack()
                                        Toast.makeText(
                                            context,
                                            "Save Time $time $time2",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    } else if (month == "04" && day.toInt() <= 30) {
                                        val timetable = MyObject.timeTable
                                            timetable.date = "$year-$month-$day"
                                            timetable.fromTime = timeSave
                                            timetable.untilTime = timeSave2
                                            timetable.type = spinner.selectedItemPosition.toString()

                                        appDataBase.biletDuo().updateTimeTable(timetable)
                                        findNavController().popBackStack()
                                        Toast.makeText(
                                            context,
                                            "Save Time $time $time2",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    } else if (month == "05" && day.toInt() <= 31) {
                                        val timetable = MyObject.timeTable
                                            timetable.date = "$year-$month-$day"
                                            timetable.fromTime = timeSave
                                            timetable.untilTime = timeSave2
                                            timetable.type = spinner.selectedItemPosition.toString()

                                        appDataBase.biletDuo().updateTimeTable(timetable)
                                        findNavController().popBackStack()
                                        Toast.makeText(
                                            context,
                                            "Save Time $time $time2",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    } else if (month == "06" && day.toInt() <= 30) {
                                        val timetable = MyObject.timeTable
                                            timetable.date = "$year-$month-$day"
                                            timetable.fromTime = timeSave
                                            timetable.untilTime = timeSave2
                                            timetable.type = spinner.selectedItemPosition.toString()

                                        appDataBase.biletDuo().updateTimeTable(timetable)
                                        findNavController().popBackStack()
                                        Toast.makeText(
                                            context,
                                            "Save Time $time $time2",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    } else if (month == "07" && day.toInt() <= 31) {
                                        val timetable = MyObject.timeTable
                                            timetable.date = "$year-$month-$day"
                                            timetable.fromTime = timeSave
                                            timetable.untilTime = timeSave2
                                            timetable.type = spinner.selectedItemPosition.toString()

                                        appDataBase.biletDuo().updateTimeTable(timetable)
                                        findNavController().popBackStack()
                                        Toast.makeText(
                                            context,
                                            "Save Time $time $time2",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    } else if (month == "08" && day.toInt() <= 31) {
                                        val timetable = MyObject.timeTable
                                            timetable.date = "$year-$month-$day"
                                            timetable.fromTime = timeSave
                                            timetable.untilTime = timeSave2
                                            timetable.type = spinner.selectedItemPosition.toString()

                                        appDataBase.biletDuo().updateTimeTable(timetable)
                                        findNavController().popBackStack()
                                        Toast.makeText(
                                            context,
                                            "Save Time $time $time2",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    } else if (month == "09" && day.toInt() <= 30) {
                                        val timetable = MyObject.timeTable
                                            timetable.date = "$year-$month-$day"
                                            timetable.fromTime = timeSave
                                            timetable.untilTime = timeSave2
                                            timetable.type = spinner.selectedItemPosition.toString()

                                        appDataBase.biletDuo().updateTimeTable(timetable)
                                        findNavController().popBackStack()
                                        Toast.makeText(
                                            context,
                                            "Save Time $time $time2",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    } else if (month == "10" && day.toInt() <= 31) {
                                        val timetable = MyObject.timeTable
                                            timetable.date = "$year-$month-$day"
                                            timetable.fromTime = timeSave
                                            timetable.untilTime = timeSave2
                                            timetable.type = spinner.selectedItemPosition.toString()

                                        appDataBase.biletDuo().updateTimeTable(timetable)
                                        findNavController().popBackStack()
                                        Toast.makeText(
                                            context,
                                            "Save Time $time $time2",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    } else if (month == "11" && day.toInt() <= 30) {
                                        val timetable = MyObject.timeTable
                                            timetable.date = "$year-$month-$day"
                                            timetable.fromTime = timeSave
                                            timetable.untilTime = timeSave2
                                            timetable.type = spinner.selectedItemPosition.toString()

                                        appDataBase.biletDuo().updateTimeTable(timetable)
                                        findNavController().popBackStack()
                                        Toast.makeText(
                                            context,
                                            "Save Time $time $time2",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    } else if (month == "12" && day.toInt() <= 31) {
                                        val timetable = MyObject.timeTable
                                            timetable.date = "$year-$month-$day"
                                            timetable.fromTime = timeSave
                                            timetable.untilTime = timeSave2
                                            timetable.type = spinner.selectedItemPosition.toString()

                                        appDataBase.biletDuo().updateTimeTable(timetable)
                                        findNavController().popBackStack()
                                        Toast.makeText(
                                            context,
                                            "Save Time $time $time2",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    } else {
                                        Toast.makeText(context, "no such month", Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                } else {
                                    Toast.makeText(context, "editText is wrong", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            } else {
                                Toast.makeText(
                                    context,
                                    "from smaller than until or editText is Empty",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                                Toast.makeText(
                                    context,
                                    "Save Time $time $time2",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        } else {
                            Toast.makeText(
                                context,
                                "From Time equals Until Time",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
                }
            }
        }
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun plusMinusMinute() {
        binding.apply {
            plusMinuteFrom.setOnClickListener {
                if (minute < 59) {
                    if (minute < 9) {
                        tvMinuteFrom.text = "0${++minute}"
                        minuteString = "0${minute}"
                    } else {
                        tvMinuteFrom.text = "${++minute}"
                        minuteString = "$minute"
                    }
                } else {
                    minute = 0
                    tvMinuteFrom.text = "0$minute"
                    minuteString = "0$minute"
                }
            }
            minutePlusFrom.setOnClickListener {
                if (minute < 59) {
                    if (minute < 9) {
                        tvMinuteFrom.text = "0${++minute}"
                        minuteString = "0${minute}"
                    } else {
                        tvMinuteFrom.text = "${++minute}"
                        minuteString = "${minute}"
                    }
                } else {
                    minute = 0
                    tvMinuteFrom.text = "0$minute"
                    minuteString = "0$minute"
                }
            }
            minusMinuteFrom.setOnClickListener {
                if (minute > 0) {
                    if (minute < 11) {
                        tvMinuteFrom.text = "0${--minute}"
                        minuteString = "0${minute}"
                    } else {
                        tvMinuteFrom.text = "${--minute}"
                        minuteString = "${minute}"
                    }
                } else {
                    minute = 59
                    tvMinuteFrom.text = "$minute"
                    minuteString = "$minute"
                }
            }
            minuteMinusFrom.setOnClickListener {
                if (minute > 0) {
                    if (minute < 11) {
                        tvMinuteFrom.text = "0${--minute}"
                        minuteString = "0${minute}"
                    } else {
                        tvMinuteFrom.text = "${--minute}"
                        minuteString = "${minute}"
                    }
                } else {
                    minute = 59
                    tvMinuteFrom.text = "$minute"
                    minuteString = "$minute"
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun plusMinusHours() {
        binding.apply {
            plusHoursFrom.setOnClickListener {
                if (hour < 23) {
                    if (hour < 9) {
                        tvHoursFrom.text = "0${++hour}"
                        hourString = "0${hour}"
                    } else {
                        tvHoursFrom.text = "${++hour}"
                        hourString = "${hour}"
                    }
                } else {
                    hour = 0
                    tvHoursFrom.text = "0$hour"
                    hourString = "0${hour}"
                }
            }
            hoursPlusFrom.setOnClickListener {
                if (hour < 23) {
                    if (hour < 9) {
                        tvHoursFrom.text = "0${++hour}"
                        hourString = "0${hour}"
                    } else {
                        tvHoursFrom.text = "${++hour}"
                        hourString = "${hour}"
                    }
                } else {
                    hour = 0
                    tvHoursFrom.text = "0$hour"
                    hourString = "0${hour}"
                }
            }
            minusHoursFrom.setOnClickListener {
                if (hour > 0) {
                    if (hour < 11) {
                        tvHoursFrom.text = "0${--hour}"
                        hourString = "0${hour}"
                    } else {
                        tvHoursFrom.text = "${--hour}"
                        hourString = "${hour}"
                    }
                } else {
                    hour = 23
                    tvHoursFrom.text = "$hour"
                    hourString = "$hour"
                }
            }
            hoursMinusFrom.setOnClickListener {
                if (hour > 0) {
                    if (hour < 11) {
                        tvHoursFrom.text = "0${--hour}"
                        hourString = "0${hour}"
                    } else {
                        tvHoursFrom.text = "${--hour}"
                        hourString = "${hour}"
                    }
                } else {
                    hour = 23
                    tvHoursFrom.text = "$hour"
                    hourString = "$hour"
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun plusMinusMinuteFrom() {
        binding.apply {
            plusMinute.setOnClickListener {
                if (minute2 < 59) {
                    if (minute2 < 9) {
                        tvMinute.text = "0${++minute2}"
                        minute2String = "0${minute2}"
                    } else {
                        tvMinute.text = "${++minute2}"
                        minute2String = "${minute2}"
                    }
                } else {
                    minute2 = 0
                    tvMinute.text = "0$minute2"
                    minute2String = "0${minute2}"
                }
            }
            minutePlus.setOnClickListener {
                if (minute2 < 59) {
                    if (minute2 < 9) {
                        tvMinute.text = "0${++minute2}"
                        minute2String = "0${minute2}"
                    } else {
                        tvMinute.text = "${++minute2}"
                        minute2String = "${minute2}"
                    }
                } else {
                    minute2 = 0
                    tvMinute.text = "0$minute2"
                    minute2String = "0${minute2}"
                }
            }
            minusMinute.setOnClickListener {
                if (minute2 > 0) {
                    if (minute2 < 11) {
                        tvMinute.text = "0${--minute2}"
                        minute2String = "0${minute2}"
                    } else {
                        tvMinute.text = "${--minute2}"
                        minute2String = "${minute2}"
                    }
                } else {
                    minute2 = 59
                    tvMinute.text = "$minute2"
                    minute2String = "${minute2}"
                }
            }
            minuteMinus.setOnClickListener {
                if (minute2 > 0) {
                    if (minute2 < 11) {
                        tvMinute.text = "0${--minute2}"
                        minute2String = "0${minute2}"
                    } else {
                        tvMinute.text = "${--minute2}"
                        minute2String = "${minute2}"
                    }
                } else {
                    minute2 = 59
                    tvMinute.text = "$minute2"
                    minute2String = "${minute2}"
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun plusMinusHoursFrom() {
        binding.apply {
            plusHours.setOnClickListener {
                if (hour2 < 23) {
                    if (hour2 < 9) {
                        tvHours.text = "0${++hour2}"
                        hour2String = "0${hour2}"
                    } else {
                        tvHours.text = "${++hour2}"
                        hour2String = "${hour2}"
                    }
                } else {
                    hour2 = 0
                    tvHours.text = "0$hour2"
                    hour2String = "0${hour2}"
                }
            }
            hoursPlus.setOnClickListener {
                if (hour2 < 23) {
                    if (hour2 < 9) {
                        tvHours.text = "0${++hour2}"
                        hour2String = "0${hour2}"
                    } else {
                        tvHours.text = "${++hour2}"
                        hour2String = "${hour2}"
                    }
                } else {
                    hour2 = 0
                    tvHours.text = "0$hour2"
                    hour2String = "0${hour2}"
                }
            }
            minusHours.setOnClickListener {
                if (hour2 > 0) {
                    if (hour2 < 11) {
                        tvHours.text = "0${--hour2}"
                        hour2String = "0${hour2}"
                    } else {
                        tvHours.text = "${--hour2}"
                        hour2String = "${hour2}"
                    }
                } else {
                    hour2 = 23
                    tvHours.text = "$hour2"
                    hour2String = "$hour2"
                }
            }
            hoursMinus.setOnClickListener {
                if (hour2 > 0) {
                    if (hour2 < 11) {
                        tvHours.text = "0${--hour2}"
                        hour2String = "0${hour2}"
                    } else {
                        tvHours.text = "${--hour2}"
                        hour2String = "${hour2}"
                    }
                } else {
                    hour2 = 23
                    tvHours.text = "$hour2"
                    hour2String = "$hour2"
                }
            }
        }
    }
}