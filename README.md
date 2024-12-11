# 🎫 Real-Time Event Ticketing System

## 🚀 Project Overview

A cutting-edge, real-time event ticketing system designed to revolutionize ticket management with modern technologies and concurrent processing.

## 💻 Technology Stack

### 🖥️ Frontend
- **Framework**: React.js (v18+)
- **UI Library**: Shadcn/UI
- **State Management**: React Hooks (useState)
- **Styling**: Tailwind CSS
- **Icons**: Lucide React
- **HTTP Client**: Fetch API

### 🔧 Backend
- **Framework**: Spring Boot
- **ORM**: Spring JPA
- **Database**: MySQL
- **WebSocket**: Spring WebSocket
- **JSON Processing**: Gson

### 🏗️ Build Management
- **Frontend**: npm
- **Backend**: Maven

## ✨ Key Features

### 🎟️ Ticket Management
- Real-time ticket availability tracking
- Concurrent ticket booking
- Advanced ticket pool management
- Configurable system parameters


## 📂 Project Structure
```
real-time-event-ticketing-system/
│
├── frontend/                  # React Frontend
│   ├── src/
│   │   ├── components/        # Shadcn UI Components
│   │   ├── hooks/             # Custom React Hooks
│   │   └── App.js             # Main Application
│
├── backend/                   # Spring Boot Backend
│   ├── src/main/java/
│   │   ├── config/            # Configuration Classes
│   │   ├── controller/        # REST Controllers
│   │   ├── service/           # Business Logic
│   │   └── model/             # Domain Models
│
└── README.md
```

## 🖌️ Frontend Implementation Details

### 🧩 Components Used
- **Shadcn/UI Components**:
  - `Card`: Layout and content containers
  - `Button`: Interactive controls
  - `Input`: User input fields
  - `Label`: Form field labels
  - `ScrollArea`: Scrollable content area



### 🌟 Key Frontend Features
- Dynamic system configuration form
- Real-time system start/stop controls
- Responsive design
- Error handling and notifications
- Clean, modern UI with Tailwind CSS


## 🛠️ Setup & Installation

### 🖥️ Frontend Setup
```bash
# Clone repository
git clone https://github.com/sulo2002/Real_Time_Event_Ticketing_System-.git

# Navigate to frontend
cd frontend

# Install dependencies
npm install

# Install Shadcn UI components
npx shadcn-ui@latest add card button input label

# Start development server
npm start
```

### 🔐 Environment Configuration
- Create `.env` file
- Add backend API endpoint
```
REACT_APP_API_ENDPOINT=http://localhost:8080
```

## 🔒 Security Considerations
- Input validation
- Error handling
- Secure API communication
- Environment-based configuration

## 🚀 Performance Optimization
- Minimal React re-renders
- Efficient state management
- Asynchronous API calls
- Tailwind CSS for lean styling

## 🤝 Contributing
1. Fork repository
2. Create feature branch
3. Commit changes
4. Push to branch
5. Create Pull Request

## 📄 License
MIT License

## 📞 Contact
- **Author**: Sulo
- **GitHub**: [@sulo2002](https://github.com/sulo2002)
- **Project**: [Real-Time Event Ticketing System](https://github.com/sulo2002/Real_Time_Event_Ticketing_System-)

## 🙏 Acknowledgments
- React.js Community
- Shadcn/UI Developers
- Spring Boot Ecosystem
- Open Source Contributors
