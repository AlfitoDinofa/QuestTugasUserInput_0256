package com.example.meet5prak

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormBiodataScreen() {
    var namaDepan by remember { mutableStateOf("") }
    var namaBelakang by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var tglLahir by remember { mutableStateOf("") }
    var experience by remember { mutableStateOf("") }
    var menuTerbuka by remember { mutableStateOf(false) }
    var dialogTanggal by remember { mutableStateOf(false) }
    val skillDipilih = remember { mutableStateListOf<Int>() }

    val daftarSkill = listOf(R.string.skill_android, R.string.skill_uiux, R.string.skill_web)
    val daftarExperience = stringArrayResource(R.array.daftar_experience)
    val polaTanggal = stringResource(R.string.format_tanggal)
    val bentukCard = RoundedCornerShape(
        topStart = dimensionResource(R.dimen.radius_card),
        topEnd = dimensionResource(R.dimen.radius_card)
    )
    val bentukField = RoundedCornerShape(dimensionResource(R.dimen.radius_field))
    val formSiap = namaDepan.isNotBlank() && namaBelakang.isNotBlank() &&
            email.isNotBlank() && tglLahir.isNotBlank() &&
            experience.isNotBlank() && skillDipilih.isNotEmpty()

    if (dialogTanggal) {
        val state = rememberDatePickerState()
        DatePickerDialog(
            onDismissRequest = { dialogTanggal = false },
            confirmButton = {
                TextButton(onClick = {
                    state.selectedDateMillis?.let {
                        tglLahir = SimpleDateFormat(polaTanggal, Locale.getDefault()).format(Date(it))
                    }
                    dialogTanggal = false
                }) { Text(stringResource(R.string.ok)) }
            },
            dismissButton = {
                TextButton(onClick = { dialogTanggal = false }) {
                    Text(stringResource(R.string.batal))
                }
            }
        ) { DatePicker(state = state) }
    }

    Scaffold(containerColor = colorResource(R.color.latar_layar)) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            Image(
                painter = painterResource(R.drawable.bg_header),
                contentDescription = stringResource(R.string.cd_header),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.tinggi_header))
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = dimensionResource(R.dimen.margin_atas_card))
                    .shadow(dimensionResource(R.dimen.elevasi_card), bentukCard)
                    .clip(bentukCard)
                    .background(colorResource(R.color.putih_card))
            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(dimensionResource(R.dimen.padding_layar)),
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.jarak))
                ) {
                    Text(
                        text = stringResource(R.string.judul_form),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.ungu_judul),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = stringResource(R.string.subjudul_form),
                        style = MaterialTheme.typography.bodySmall,
                        color = colorResource(R.color.abu_subjudul),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.jarak_kecil))) {
                        OutlinedTextField(
                            value = namaDepan, onValueChange = { namaDepan = it },
                            label = { Text(stringResource(R.string.nama_depan)) },
                            singleLine = true, shape = bentukField,
                            modifier = Modifier.weight(1f)
                        )
                        OutlinedTextField(
                            value = namaBelakang, onValueChange = { namaBelakang = it },
                            label = { Text(stringResource(R.string.nama_belakang)) },
                            singleLine = true, shape = bentukField,
                            modifier = Modifier.weight(1f)
                        )
                    }

