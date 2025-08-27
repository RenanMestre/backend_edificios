
document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.getElementById('loginForm');
    
    if (loginForm) {
        loginForm.addEventListener('submit', function(e) {
            e.preventDefault();
            
            const name = document.getElementById('name').value;
            const email = document.getElementById('email').value;
            const password = document.getElementById('password').value;
            
            
            if (name && email && password) {
                localStorage.setItem('currentUser', JSON.stringify({
                    email: email,
                    name: name
                }));
                
                window.location.href = 'dashboard.html';
            } else {
                alert('Por favor, preencha todos os campos!');
            }
        });
    }

    if (window.location.pathname.includes('dashboard.html')) {
        loadDashboard();
    }
});

function loadDashboard() {
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    
    if (!currentUser) {
        window.location.href = 'index.html';
        return;
    }

    document.getElementById('userName').textContent = `Olá, ${currentUser.name}!`;

    updateStats();
    loadOrders();
}

function updateStats() {
    const stats = {
        pending: Math.floor(Math.random() * 5) + 1,
        completed: Math.floor(Math.random() * 20) + 5,
        total: 0
    };
    
    stats.total = stats.pending + stats.completed;
    
    document.getElementById('pendingDeliveries').textContent = stats.pending;
    document.getElementById('completedDeliveries').textContent = stats.completed;
    document.getElementById('totalOrders').textContent = stats.total;
}

function loadOrders() {
    const ordersList = document.getElementById('ordersList');

    const sampleOrders = [
        {
            id: '#CP004',
            status: 'pending',
            statusText: 'Saiu para entrega',
            description: 'Eletrônicos - Brasília/DF',
            date: '25/08/2025'
        },
        {
            id: '#CP005',
            status: 'delivered',
            statusText: 'Entregue',
            description: 'Medicamentos - Curitiba/PR',
            date: 'Entregue em: 20/08/2025'
        }
    ];

    sampleOrders.forEach(order => {
        const orderElement = document.createElement('div');
        orderElement.className = 'order-item';
        orderElement.innerHTML = `
            <div class="order-info">
                <span class="order-id">${order.id}</span>
                <span class="order-status status-${order.status}">${order.statusText}</span>
            </div>
            <div class="order-details">
                <p>${order.description}</p>
                <small>${order.date}</small>
            </div>
        `;
        ordersList.appendChild(orderElement);
    });
}

function logout() {
    localStorage.removeItem('currentUser');
    window.location.href = 'index.html';
}

function checkAuth() {
    if (window.location.pathname.includes('dashboard.html')) {
        const currentUser = JSON.parse(localStorage.getItem('currentUser'));
        if (!currentUser) {
            window.location.href = 'index.html';
        }
    }
}

checkAuth();
