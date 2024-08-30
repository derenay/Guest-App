document.addEventListener("DOMContentLoaded", function () {
    fetch('/guest')
        .then(response => response.json())
        .then(data => {
            const tableBody = document.getElementById('guestsTableBody');
            tableBody.innerHTML = '';
            data.forEach(guest => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${guest.firstname}</td>
                    <td>${guest.lastname}</td>
                    <td>${guest.email}</td>
                    <td>${guest.phoneNumber}</td>
                    <td>${guest.visitReason}</td>
                    <td>${guest.visitStartDate}</td>
                    <td>${guest.visitEndDate}</td>
                    <td>${guest.isAllowed}</td>
                    <td><button class="btn btn-warning btn-sm" onclick="updateGuest(${guest.id})" data-bs-toggle="modal" data-bs-target="#updateModal">Update</button></td>
                `;
                tableBody.appendChild(row);
            });
        });
});

function updateGuest(id) {
    const csrfToken = document.querySelector('meta[name="csrf-token"]').getAttribute('content');
    fetch(`/guest/${id}`)
        .then(response => response.json())
        .then(guest => {
            document.getElementById('update-id').value = guest.id;
            document.getElementById('update-firstname').value = guest.firstname;
            document.getElementById('update-lastname').value = guest.lastname;
            document.getElementById('update-email').value = guest.email;
            document.getElementById('update-phoneNumber').value = guest.phoneNumber;
            document.getElementById('update-visitReason').value = guest.visitReason;
            document.getElementById('update-visitStartDate').value = guest.visitStartDate;
            document.getElementById('update-visitEndDate').value = guest.visitEndDate;
            document.getElementById('update-isAllowed').value = guest.isAllowed;
        });
}

function submitUpdate() {
    const csrfToken = document.querySelector('meta[name="csrf-token"]').getAttribute('content');
    const guestData = {
        id: document.getElementById('update-id').value,
        firstname: document.getElementById('update-firstname').value,
        lastname: document.getElementById('update-lastname').value,
        email: document.getElementById('update-email').value,
        phoneNumber: document.getElementById('update-phoneNumber').value,
        visitReason: document.getElementById('update-visitReason').value,
        visitStartDate: document.getElementById('update-visitStartDate').value,
        visitEndDate: document.getElementById('update-visitEndDate').value,
        isAllowed: document.getElementById('update-isAllowed').value
    };

    fetch(`/guest/update/${guestData.id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrfToken
        },
        body: JSON.stringify(guestData)
    })
        .then(response => response.json().then(data => {
            if (response.ok) {
                alert('Guest updated successfully!');
                location.reload();
            } else {
                alert('Update failed: ' + data.message);
            }
        }))
        .catch(error => console.error('Error updating guest:', error));
}

function submitAdd() {
    const csrfToken = document.querySelector('meta[name="csrf-token"]').getAttribute('content');
    const guestData = {
        firstname: document.getElementById('add-firstname').value.trim(),
        lastname: document.getElementById('add-lastname').value.trim(),
        email: document.getElementById('add-email').value.trim(),
        phoneNumber: document.getElementById('add-phoneNumber').value.trim(),
        visitReason: document.getElementById('add-visitReason').value.trim(),
        visitStartDate: document.getElementById('add-visitStartDate').value.trim(),
        visitEndDate: document.getElementById('add-visitEndDate').value.trim(),
        isAllowed: document.getElementById('add-isAllowed').value
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

