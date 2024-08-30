function submitAdd2() {
    const csrfToken = document.querySelector('meta[name="csrf-token"]').getAttribute('content');
    const guestData = {
        firstname: document.getElementById('firstname').value.trim(),
        lastname: document.getElementById('lastname').value.trim(),
        email: document.getElementById('email').value.trim(),
        phoneNumber: document.getElementById('phoneNumber').value.trim(),
        visitReason: document.getElementById('visitReason').value.trim(),
        visitStartDate: document.getElementById('visitStartDate').value.trim(),
        visitEndDate: document.getElementById('visitEndDate').value.trim(),
        nationalNumber: document.getElementById('nationalNumber').value.trim(),
        checkIn: document.getElementById('checkIn').value,
        isAllowed: document.getElementById('isAllowed').value
    };

    if (!guestData.firstname || !guestData.lastname || !guestData.phoneNumber) {
        alert('Please fill out all required fields.');
        return;
    }

    fetch('/guest', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrfToken
        },
        body: JSON.stringify(guestData)
    })
        .then(response => response.json().then(data => {
            if (response.ok) {
                alert('Guest added successfully!');
                location.reload();
            } else {
                alert('Add failed: ' + (data.message || 'Unknown error'));
            }
        }))
        .catch(error => console.error('Error adding guest:', error));
}
