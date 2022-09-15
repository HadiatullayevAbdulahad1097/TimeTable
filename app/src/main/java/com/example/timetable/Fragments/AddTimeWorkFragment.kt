package com.example.timetable.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.timetable.Models.MyObject
import com.example.timetable.Models.TimeTable
import com.example.timetable.Models.TimeWork
import com.example.timetable.SqlDB.MyDbHelper
import com.example.timetable.Database.AppDataBase
import com.example.timetable.databinding.FragmentAddTimeWorkBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AddTimeWorkFragment : Fragment() {
    lateinit var binding: FragmentAddTimeWorkBinding
    lateinit var myDbHelper: MyDbHelper
    lateinit var list: ArrayList<TimeWork>
    lateinit var list2: ArrayList<TimeTable>
    lateinit var appDataBase: AppDataBase
    var hour = 0
    var minute = 0
    var hour2 = 0
    var minute2 = 0
    var hourString = "00"
    var minuteString = "00"
    var hour2String = "00"
    var minute2String = "00"

    @SuppressLint("NewApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTimeWorkBinding.inflate(layoutInflater)

        myDbHelper = MyDbHelper(binding.root.context)

        appDataBase = AppDataBase.getInstance(binding.root.context)

        list = myDbHelper.readTimeWork()

        list2 = appDataBase.biletDuo().getAllTimeTable() as ArrayList

        binding.apply {
            when (MyObject.isAddTimeWork) {
                true -> {
                    plusMinusHours()
                    plusMinusHoursFrom()
                    plusMinusMinute()
                    plusMinusMinuteFrom()

                    binding.btnSave.setOnClickListener {
                        val current = LocalDateTime.now()
                        val formatter = DateTimeFormatter.ofPattern("yy-MM-dd")
                        val formatted = current.format(formatter)

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
                        val name = edtName.text.toString().trim()

                        if (time != time2) {
                            if (time.toInt() < time2.toInt() && name.isNotEmpty()) {
                                val timeWork = TimeWork(
                                    MyObject.timeTable.date,
                                    name,
                                    timeSave,
                                    timeSave2,
                                    MyObject.position,
                                    MyObject.pagerPosition
                                )
                                myDbHelper.addTimeWork(timeWork)
                                list.add(timeWork)
                                findNavController().popBackStack()
                            } else Toast.makeText(
                                context,
                                "EditText is Empty or $time is smaller to $time2",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else Toast.makeText(context, "$time equals $time2", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                false -> {
                    val froms = MyObject.TimeWork.from
                    val untils = MyObject.TimeWork.until

                    hourString = froms.toString().substring(0, froms.toString().indexOf(':'))
                    tvHoursFrom.text = hourString

                    if (hourString.substring(0, 1) == "0") {
                        hour = hourString.substring(1, 2).toInt()
                    } else {
                        hour = hourString.toInt()
                    }

                    minuteString = froms.toString()
                        .substring(froms.toString().indexOf(':') + 1, froms.toString().length)
                    tvMinuteFrom.text = minuteString

                    if (minuteString == "00") {
                        minute = minuteString.substring(1, 2).toInt()
                    } else {
                        minute = minuteString.toInt()
                    }

                    hour2String = untils.toString().substring(0, untils.toString().indexOf(':'))
                    tvHours.text = hour2String

                    if (hour2String.substring(0, 1) == "0") {
                        hour2 = hour2String.substring(1, 2).toInt()
                    } else {
                        hour2 = hour2String.toInt()
                    }

                    minute2String = untils.toString()
                        .substring(untils.toString().indexOf(':') + 1, untils.toString().length)
                    tvMinute.text = minute2String

                    if (minute2String == "00") {
                        minute2 = minute2String.substring(1, 2).toInt()
                    } else {
                        minute2 = minute2String.toInt()
                    }

                    edtName.setText(MyObject.TimeWork.name)

                    plusMinusHours()
                    plusMinusHoursFrom()
                    plusMinusMinute()
                    plusMinusMinuteFrom()

                    btnSave.setOnClickListener {
                        val current = LocalDateTime.now()
                        val formatter = DateTimeFormatter.ofPattern("yy-MM-dd")
                        val formatted = current.format(formatter)

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
                        val name = edtName.text.toString().trim()

                        if (time != time2) {
                            if (time.toInt() < time2.toInt() && name.isNotEmpty()) {
                                val timeWork = MyObject.TimeWork
                                timeWork.date = MyObject.timeTable.date
                                timeWork.name = name
                                timeWork.from = timeSave
                                timeWork.until = timeSave2
                                timeWork.type = MyObject.position
                                myDbHelper.updateTimeWork(timeWork)
                                findNavController().popBackStack()
                            } else Toast.makeText(
                                context,
                                "EditText is Empty or $time is smaller to $time2",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else Toast.makeText(context, "$time equals $time2", Toast.LENGTH_SHORT)
                            .show()
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