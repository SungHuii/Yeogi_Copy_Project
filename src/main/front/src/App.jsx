import {useEffect, useState} from "react";
import axios from "axios";
import Header from "./components/Header/Header";

function App() {
    const [hello, setHello] = useState('');

    useEffect(() => {
        axios.get('http://localhost:8080/api/test')
            .then((res) => {
                setHello(res.data);
            })
    }, []);
    return (
        <div className="App">
            <Header />
            백엔드 데이터 : {hello}
        </div>
    );
}

export default App;