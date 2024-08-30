document.addEventListener("DOMContentLoaded", function () {
    fetch('/guest/approval')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok.');
            }
            return response.json();
        })
        .then(data => {
            const guestsPendingTableBody = document.getElementById('guestsPendingTableBody');
            guestsPendingTableBody.innerHTML = '';
            data.forEach(guest => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${guest.firstname}</td>
                    <td>${guest.lastname}</td>
                    <td>${guest.visitReason}</td>
                    <td>${guest.isAllowed}</td>
                    <td><button class="btn btn-success btn-sm" onclick="approveGuest(${guest.id})">Approve</button></td>
                `;
                guestsPendingTableBody.appendChild(row);
            });
        })
        .catch(error => console.error('Error fetching guests:', error));
});

function approveGuest(id) {
    const csrfToken = document.querySelector('meta[name="csrf-token"]').getAttribute('content');
    const guestData = {
        id: id
    };

    fetch(`/guest/approval`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrfToken
        },
        body: JSON.stringify(guestData)
    })
        .then(response => {
            if (!response.ok) {
                return response.text().then(text => {
                    console.error('Response text:', text);
                    throw new Error(text || 'Update failed');
                });
            }
            return response.json();
        })
        .then(data => {
            alert(data.message); // Use the message from the response
            location.reload();
        })
        .catch(error => console.error('Error updating guest:', error));
}

