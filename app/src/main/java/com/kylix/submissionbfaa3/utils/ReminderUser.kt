package com.kylix.submissionbfaa3.utils

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.kylix.submissionbfaa3.R
import com.kylix.submissionbfaa3.activities.MainActivity
import com.shashank.sony.fancytoastlib.FancyToast
import java.util.*

class ReminderUser: BroadcastReceiver() {
    companion object{
        private const val REMINDER_CODE = 101
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        showNotification(context)
    }

    private fun showNotification(context: Context?){
        val channelId = "reminder_channel"
        val channelName = "reminder_github_user"

        val title = context?.resources?.getString(R.string.reminder_title)
        val message = context?.resources?.getString(R.string.reminder_message)

        val intent= Intent(context, MainActivity::class.java)
        val pendingIntent = TaskStackBuilder.create(context)
            .addParentStack(MainActivity::class.java)
            .addNextIntent(intent)
            .getPendingIntent(REMINDER_CODE, PendingIntent.FLAG_UPDATE_CURRENT)

        val notificationManagerCompat =
            context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val mBuilder = NotificationCompat.Builder(context, channelId)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.github_mark)
            .setContentTitle(title)
            .setContentText(message)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setColor(ContextCompat.getColor(context, android.R.color.transparent))
            .setSound(alarmSound)
            .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            channel.apply {
                enableVibration(true)
                vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)
            }
            mBuilder.setChannelId(channelId)
            notificationManagerCompat.createNotificationChannel(channel)
        }
        val notification = mBuilder.build()
        notificationManagerCompat.notify(REMINDER_CODE, notification)
    }

    fun setRepeatingReminder(context: Context?){
        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent: PendingIntent = Intent(context, ReminderUser::class.java).let {
            PendingIntent.getBroadcast(context, REMINDER_CODE, it, 0)
        }

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 9)
        }

        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            intent
        )

        FancyToast.makeText(context, context.getString(R.string.reminder_on), FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show()
    }

    fun cancelAlarm(context: Context?){
        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, ReminderUser::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, REMINDER_CODE, intent, 0)
        pendingIntent.cancel()

        alarmManager.cancel(pendingIntent)

        FancyToast.makeText(context, context.getString(R.string.reminder_off), FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show()
    }
}