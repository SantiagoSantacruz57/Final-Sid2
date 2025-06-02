const username = localStorage.getItem('username');
if (!username) {
  window.location.href = '/auth/login';
}
console.log(username);  // Outputs: user1

// Estado global de la aplicación
let selectedPlanId = null;

// URLs de la API
const API_BASE = window.location.origin;
const API_ENDPOINTS = {
    evalPlans: `${API_BASE}/evalplans`,
    comments: `${API_BASE}/comments`,
    addComment: (planId) => `${API_BASE}/comments/evalplan/${planId}`,
    planComments: (planId) => `${API_BASE}/comments/evalplan/${planId}`
};

// Elementos del DOM
const evalPlansContainer = document.getElementById('evalPlansContainer');
const selectedPlanInfo = document.getElementById('selectedPlanInfo');
const addCommentForm = document.getElementById('addCommentForm');
const commentForm = document.getElementById('commentForm');
const commentsContainer = document.getElementById('commentsContainer');

// Inicializar la aplicación
document.addEventListener('DOMContentLoaded', function() {
    loadEvalPlans();
    
    // Configurar el formulario de comentarios
    commentForm.addEventListener('submit', handleCommentSubmit);
});

// Cargar todos los planes de evaluación
async function loadEvalPlans() {
    try {
        showLoading(evalPlansContainer, 'Cargando planes de evaluación...');
        
        const response = await fetch(API_ENDPOINTS.evalPlans);
        if (!response.ok) {
            throw new Error(`Error: ${response.status}`);
        }
        
        const plans = await response.json();
        displayEvalPlans(plans);
        
    } catch (error) {
        console.error('Error cargando planes:', error);
        showError(evalPlansContainer, 'Error al cargar los planes de evaluación');
    }
}

// Mostrar los planes de evaluación
function displayEvalPlans(plans) {
    if (plans.length === 0) {
        evalPlansContainer.innerHTML = '<p class="loading">No hay planes de evaluación disponibles</p>';
        return;
    }
    
    evalPlansContainer.innerHTML = plans.map(plan => `
        <div class="eval-plan-card" onclick="selectPlan('${plan.id}', '${plan.name}', '${plan.authorId}')">
            <h3>${plan.name}</h3>
            <p><strong>Autor:</strong> ${plan.authorId}</p>
            <p><strong>Materia:</strong> ${plan.subjectId}</p>
            <p><strong>Comentarios:</strong> ${plan.commentIds ? plan.commentIds.length : 0}</p>
            <p><strong>Creado:</strong> ${formatDate(plan.createdAt)}</p>
        </div>
    `).join('');
}

// Seleccionar un plan de evaluación
function selectPlan(planId, planName, authorId) {
    // Actualizar estado
    selectedPlanId = planId;
    
    // Actualizar UI - resaltar plan seleccionado
    document.querySelectorAll('.eval-plan-card').forEach(card => {
        card.classList.remove('selected');
    });
    event.currentTarget.classList.add('selected');
    
    // Mostrar información del plan seleccionado
    selectedPlanInfo.innerHTML = `
        <h4><i class="fas fa-clipboard-check"></i> Plan Seleccionado</h4>
        <p><strong>Nombre:</strong> ${planName}</p>
        <p><strong>Autor:</strong> ${authorId}</p>
        <p><strong>ID:</strong> ${planId}</p>
    `;
    
    // Mostrar formulario para agregar comentarios
    addCommentForm.style.display = 'block';
    
    // Cargar comentarios del plan
    loadPlanComments(planId);
}

// Cargar comentarios de un plan específico
async function loadPlanComments(planId) {
    try {
        showLoading(commentsContainer, 'Cargando comentarios...');
        
        const response = await fetch(API_ENDPOINTS.planComments(planId));
        if (!response.ok) {
            throw new Error(`Error: ${response.status}`);
        }
        
        const comments = await response.json();
        displayComments(comments);
        
    } catch (error) {
        console.error('Error cargando comentarios:', error);
        showError(commentsContainer, 'Error al cargar los comentarios');
    }
}

// Mostrar comentarios
function displayComments(comments) {
    if (comments.length === 0) {
        commentsContainer.innerHTML = '<p class="loading">No hay comentarios para este plan</p>';
        return;
    }
    
    commentsContainer.innerHTML = comments.map(comment => `
        <div class="comment-card">
            <div class="comment-header">
                <span class="comment-user">
                    <i class="fas fa-user-circle"></i> ${comment.user}
                </span>
            </div>
            <div class="comment-content">${comment.content}</div>
        </div>
    `).join('');
}

// Manejar envío de comentario
async function handleCommentSubmit(event) {
    event.preventDefault();
    
    if (!selectedPlanId) {
        showMessage('error', 'Por favor selecciona un plan de evaluación primero');
        return;
    }
    
    const userName = document.getElementById('userName').value.trim();
    const commentContent = document.getElementById('commentContent').value.trim();
    
    if (!userName || !commentContent) {
        showMessage('error', 'Por favor completa todos los campos');
        return;
    }
    
    try {
        // Deshabilitar botón durante envío
        const submitButton = event.target.querySelector('button[type="submit"]');
        const originalText = submitButton.innerHTML;
        submitButton.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Enviando...';
        submitButton.disabled = true;
        
        const commentData = {
            user: userName,
            content: commentContent
        };
        
        const response = await fetch(API_ENDPOINTS.addComment(selectedPlanId), {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(commentData)
        });
        
        if (!response.ok) {
            throw new Error(`Error: ${response.status}`);
        }
        
        const newComment = await response.json();
        
        // Mostrar mensaje de éxito
        showMessage('success', 'Comentario agregado exitosamente');
        
        // Limpiar formulario
        commentForm.reset();
        
        // Recargar comentarios
        loadPlanComments(selectedPlanId);
        
        // Recargar planes para actualizar contador
        loadEvalPlans();
        
    } catch (error) {
        console.error('Error enviando comentario:', error);
        showMessage('error', 'Error al enviar el comentario. Inténtalo de nuevo.');
    } finally {
        // Rehabilitar botón
        const submitButton = event.target.querySelector('button[type="submit"]');
        submitButton.innerHTML = originalText;
        submitButton.disabled = false;
    }
}

// Utilidades
function showLoading(container, message) {
    container.innerHTML = `<div class="loading"><i class="fas fa-spinner fa-spin"></i> ${message}</div>`;
}

function showError(container, message) {
    container.innerHTML = `<div class="error"><i class="fas fa-exclamation-triangle"></i> ${message}</div>`;
}

function showMessage(type, message) {
    // Remover mensajes anteriores
    const existingMessages = document.querySelectorAll('.error, .success');
    existingMessages.forEach(msg => msg.remove());
    
    // Crear nuevo mensaje
    const messageElement = document.createElement('div');
    messageElement.className = type;
    messageElement.innerHTML = `<i class="fas fa-${type === 'success' ? 'check-circle' : 'exclamation-triangle'}"></i> ${message}`;
    
    // Insertar antes del formulario
    addCommentForm.insertBefore(messageElement, addCommentForm.firstChild);
    
    // Remover mensaje después de 5 segundos
    setTimeout(() => {
        messageElement.remove();
    }, 5000);
}



function formatDate(dateString) {
    if (!dateString) return 'Fecha no disponible';
    
    try {
        const date = new Date(dateString);
        return date.toLocaleDateString('es-ES', {
            year: 'numeric',
            month: 'short',
            day: 'numeric',
            hour: '2-digit',
            minute: '2-digit'
        });
    } catch (error) {
        return 'Fecha inválida';
    }
} 