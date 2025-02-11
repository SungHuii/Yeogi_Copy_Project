import {BrowserRouter, Route, Routes} from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import ProfilePage from "./pages/ProfilePage";
import MainPage from "./pages/MainPage";

function App() {
    // sihong 20250211 - 브라우저 라우팅을 위해 react-router 적용
    return (
        <BrowserRouter>
            <Routes>
                {/* http://localhost:5173 */}
                <Route path="/" element={<MainPage />} /> 
                {/* http://localhost:5173/login */}
                <Route path="/login" element={<LoginPage />} />
                {/* http://localhost:5173/profile */}
                <Route path="/profile" element={<ProfilePage />} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;