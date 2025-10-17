const API_BASE = 'http://localhost:8080/api';

async function fetchHotels(){
  const res = await fetch(`${API_BASE}/hotels`);
  const hotels = await res.json();
  return hotels;
}

function renderHotels(hotels){
  const container = document.getElementById('hotelsContainer');
  container.innerHTML = '';
  const select = document.getElementById('hotelSelect');
  select.innerHTML = '';
  hotels.forEach(h => {
    const card = document.createElement('div');
    card.className = 'card';
    card.innerHTML = `<h3>${h.name}</h3>
      <p>${h.city}</p>
      <p>₹${h.pricePerNight} per night</p>
      <p>Rooms: ${h.availableRooms}</p>`;
    container.appendChild(card);

    const opt = document.createElement('option');
    opt.value = h.id;
    opt.text = `${h.name} — ${h.city} (₹${h.pricePerNight})`;
    select.appendChild(opt);
  });
}

async function init(){
  try {
    const hotels = await fetchHotels();
    renderHotels(hotels);
  } catch(e){
    console.error(e);
    document.getElementById('hotelsContainer').innerText = 'Failed to load hotels.';
  }
}

document.getElementById('bookingForm').addEventListener('submit', async (e) => {
  e.preventDefault();
  const payload = {
    hotelId: parseInt(document.getElementById('hotelSelect').value),
    customerName: document.getElementById('name').value,
    customerEmail: document.getElementById('email').value,
    checkIn: document.getElementById('checkIn').value,
    checkOut: document.getElementById('checkOut').value,
    roomsBooked: parseInt(document.getElementById('rooms').value)
  };

  try {
    const res = await fetch(`${API_BASE}/bookings`, {
      method: 'POST',
      headers:{ 'Content-Type':'application/json' },
      body: JSON.stringify(payload)
    });

    const messageDiv = document.getElementById('message');
    if(!res.ok){
      const text = await res.text();
      messageDiv.innerText = 'Booking failed: ' + text;
    } else {
      const booking = await res.json();
      messageDiv.innerText = `Booked! Booking id: ${booking.id}`;
      // refresh hotels
      const hotels = await fetchHotels();
      renderHotels(hotels);
    }
  } catch(err){
    console.error(err);
    document.getElementById('message').innerText = 'Error while booking';
  }
});

// init
init();
