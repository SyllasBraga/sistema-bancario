import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import PessoaPage from "./pages/PessoaPage";
import ContaPage from "./pages/ContaPage";
import MovimentacaoPage from "./pages/MovimentacaoPage";
import Header from "./components/Header";

const App = () => {
  return (
    <Router>
      <Header/>
      <Routes>
        <Route path="/" element={<PessoaPage />} />
        <Route path="/pessoa-page" element={<PessoaPage />} />
        <Route path="/conta-page" element={<ContaPage />} />
        <Route path="/movimentacao-page" element={<MovimentacaoPage />} />
      </Routes>
    </Router>
  );
};

export default App;