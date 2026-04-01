package com.gmail.kaminskysem.PersnalHelper.Timer

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import android.media.MediaPlayer
import android.os.IBinder
import android.view.LayoutInflater
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.bcgdv.asia.lib.ticktock.TickTockView
import com.gmail.kaminskysem.PersnalHelper.R
import com.gmail.kaminskysem.PersnalHelper.Timer.Service.TimerService
import kotlinx.coroutines.delay
import timber.log.Timber
import java.util.Calendar

object TimerTImerFragment {
    const val TIMER_WORK = "TimerWork"
    const val TIMER_REST = "TimerRest"
    const val TIME_FOR_CIRCLE_WORK = "TimerWorkCircle"
    const val TIME_FOR_CIRCLE_REST = "TimerRestCircle"
    const val BROADCAST_ACTION = " com.gmail.kaminskysem.PersnalHelper.Timer"
}

enum class WidgetActionType {
    START, STOP, NONE
}

@SuppressLint("UnspecifiedRegisterReceiverFlag")
@Composable
fun TimerTimerScreen() {
    val context = LocalContext.current

    var stringWorkTimer by remember { mutableStateOf("") }
    var stringRestTimer by remember { mutableStateOf("") }
    val initialTimeText = stringResource(R.string.time)
    var timeText by remember { mutableStateOf(initialTimeText) }
    var showHelp by remember { mutableStateOf(true) }

    val isStartEnabled = stringWorkTimer.isNotEmpty() && stringRestTimer.isNotEmpty()

    val mediaPlayer = remember { MediaPlayer.create(context, R.raw.ticking_clock) }

    DisposableEffect(Unit) {
        onDispose {
            try {
                mediaPlayer.stop()
                mediaPlayer.release()
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    LaunchedEffect(Unit) {
        showHelp = true
        delay(5000)
        showHelp = false
    }

    var iEnd by remember { mutableStateOf(-1) }
    var widgetActionId by remember { mutableStateOf(0L) }
    var widgetActionType by remember { mutableStateOf(WidgetActionType.NONE) }

    // Service connection state
    var bound by remember { mutableStateOf(false) }
    var timerService by remember { mutableStateOf<TimerService?>(null) }
    
    val serviceConnection = remember {
        object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                val binder = service as? TimerService.MyBinder
                timerService = binder?.service
                bound = true
                Timber.d("TimerFragment connected")
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                bound = false
                timerService = null
                Timber.d("TimerFragment Disconnected")
            }
        }
    }

    DisposableEffect(context) {
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(ctx: Context, intent: Intent) {
                intent.getStringExtra(TimerTImerFragment.TIMER_WORK)?.let {
                    timeText = it
                }

                intent.getStringExtra(TimerTImerFragment.TIME_FOR_CIRCLE_WORK)?.let {
                    iEnd = it.toIntOrNull() ?: -1
                    widgetActionId = System.currentTimeMillis()
                    widgetActionType = WidgetActionType.START
                }

                intent.getStringExtra(TimerTImerFragment.TIME_FOR_CIRCLE_REST)?.let {
                    iEnd = it.toIntOrNull() ?: -1
                    widgetActionId = System.currentTimeMillis()
                    widgetActionType = WidgetActionType.START
                }
            }
        }
        val filter = IntentFilter(TimerTImerFragment.BROADCAST_ACTION)
        try {
            ContextCompat.registerReceiver(
                context,
                receiver,
                filter,
                ContextCompat.RECEIVER_NOT_EXPORTED
            )
        } catch (e: Exception) {
            context.registerReceiver(receiver, filter)
        }

        onDispose {
            context.unregisterReceiver(receiver)
            if (bound) {
                try {
                    context.unbindService(serviceConnection)
                } catch (e: Exception) {}
            }
        }
    }

    fun startService() {
        val intentStart = Intent(context, TimerService::class.java).apply {
            putExtra(TimerTImerFragment.TIMER_WORK, stringWorkTimer)
            putExtra(TimerTImerFragment.TIMER_REST, stringRestTimer)
            action = TimerService.ACTION_START_TIMER
        }
        context.startService(intentStart)
        try {
            mediaPlayer.start()
        } catch (e: Exception) {}

        context.bindService(intentStart, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    fun stopService() {
        val intentStop = Intent(context, TimerService::class.java).apply {
            action = TimerService.ACTION_STOP_TIMER
        }
        context.startService(intentStop)
        widgetActionId = System.currentTimeMillis()
        widgetActionType = WidgetActionType.STOP
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = timeText,
            color = Color.White,
            fontSize = 24.sp,
            modifier = Modifier
                .background(Color(0x80FFFFFF))
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        AndroidView(
            modifier = Modifier.size(150.dp),
            factory = { ctx ->
                val layoutId = ctx.resources.getIdentifier("layout_ticktock", "layout", ctx.packageName)
                val container = LayoutInflater.from(ctx).inflate(layoutId, null)
                val viewId = ctx.resources.getIdentifier("view_ticktock_countdown", "id", ctx.packageName)
                val view = container.findViewById<TickTockView>(viewId)
                view?.setOnTickListener(object : TickTockView.OnTickListener {
                    override fun getText(timeRemainingInMillis: Long): String {
                        val seconds = (timeRemainingInMillis / 1000) % 60
                        val minutes = ((timeRemainingInMillis / (1000 * 60)) % 60)
                        val hours = ((timeRemainingInMillis / (1000 * 60 * 60)) % 24)
                        val days = (timeRemainingInMillis / (1000 * 60 * 60 * 24))
                        val hasDays = days > 0
                        val d1 = if (hasDays) days else hours
                        val d2 = if (hasDays) hours else minutes
                        val d3 = if (hasDays) minutes else seconds
                        val s1 = if (hasDays) "d" else "h"
                        val s2 = if (hasDays) "h" else "m"
                        val s3 = if (hasDays) "m" else "s"
                        return String.format("%02d%s %02d%s %02d%s", d1, s1, d2, s2, d3, s3)
                    }
                })
                container
            },
            update = { container ->
                val viewId = container.context.resources.getIdentifier("view_ticktock_countdown", "id", container.context.packageName)
                val view = container.findViewById<TickTockView>(viewId)
                if (view?.tag != widgetActionId) {
                    view?.tag = widgetActionId
                    when (widgetActionType) {
                        WidgetActionType.START -> {
                            view?.refreshDrawableState()
                            view?.stop()
                            if (iEnd >= 0) {
                                val start = Calendar.getInstance()
                                val end = Calendar.getInstance().apply { add(Calendar.MINUTE, iEnd) }
                                view?.start(start, end)
                            }
                        }
                        WidgetActionType.STOP -> {
                            view?.refreshDrawableState()
                            view?.stop()
                        }
                        WidgetActionType.NONE -> {}
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                if (showHelp) {
                    Text(
                        text = "set time to WORK",
                        color = Color.Black,
                        modifier = Modifier
                            .background(Color.White)
                            .padding(4.dp)
                    )
                }
                OutlinedTextField(
                    value = stringWorkTimer,
                    onValueChange = { stringWorkTimer = it },
                    label = { Text(stringResource(R.string.timer_time_to_work), color = Color.White) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.width(120.dp),
                    textStyle = TextStyle(color = Color.White, fontWeight = FontWeight.Bold),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color(0x80FFFFFF)
                    )
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                if (showHelp) {
                    Text(
                        text = "set time to REST",
                        color = Color.Black,
                        modifier = Modifier
                            .background(Color.White)
                            .padding(4.dp)
                    )
                }
                OutlinedTextField(
                    value = stringRestTimer,
                    onValueChange = { stringRestTimer = it },
                    label = { Text(stringResource(R.string.timer_time_to_rest), color = Color.White) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.width(120.dp),
                    textStyle = TextStyle(color = Color.White, fontWeight = FontWeight.Bold),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color(0x80FFFFFF)
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { stopService() },
                modifier = Modifier.padding(end = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
            ) {
                Text(stringResource(R.string.stop).uppercase(), color = Color.White)
            }
            Button(
                onClick = { startService() },
                enabled = isStartEnabled,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Yellow,
                    contentColor = Color.Black,
                    disabledContainerColor = Color(0x80FFFF00)
                )
            ) {
                Text(stringResource(R.string.start).uppercase(), fontWeight = FontWeight.Bold)
            }
        }
    }
}
