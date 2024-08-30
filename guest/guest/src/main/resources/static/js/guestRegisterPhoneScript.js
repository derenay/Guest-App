function submitAdd2() {
    const formData = new FormData();

    // Gather guest data from the form
    const guest = {
        firstname: document.getElementById('firstname').value,
        lastname: document.getElementById('lastname').value,
        email: document.getElementById('email').value,
        phoneNumber: document.getElementById('phoneNumber').value,
        visitReason: document.getElementById('visitReason').value,
        visitStartDate: document.getElementById('visitStartDate').value,
        visitEndDate: document.getElementById('visitEndDate').value,
        nationalNumber: document.getElementById('nationalNumber').value,
        isAllowed: document.getElementById('isAllowed').value,
        checkIn: document.getElementById('checkIn').value
    };

    // Append the guest object as a JSON string
    formData.append('guest', new Blob([JSON.stringify(guest)], { type: "application/json" }));

    // Append the file
    const fileInput = document.getElementById('photo');
    if (fileInput.files.length > 0) {
        formData.append('file', fileInput.files[0]);
    }

    // Send the form data
    fetch('/guest', {
        method: 'POST',
        body: formData,
        headers: {
            'X-CSRF-TOKEN': document.querySelector('meta[name="csrf-token"]').getAttribute('content')
        }
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}
