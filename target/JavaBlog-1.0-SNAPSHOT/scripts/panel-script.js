function showGeneralTab() {
    document.getElementById('general-view').style.display = 'block';
    document.getElementById('settings-view').style.display = 'none';
    document.getElementById('statistics-view').style.display = 'none';
}

function showStatisticsTab() {
    document.getElementById('general-view').style.display = 'none';
    document.getElementById('settings-view').style.display = 'none';
    document.getElementById('statistics-view').style.display = 'block';
}

function showSettingsTab() {
    document.getElementById('general-view').style.display = 'none';
    document.getElementById('settings-view').style.display = 'block';
    document.getElementById('statistics-view').style.display = 'none';
}