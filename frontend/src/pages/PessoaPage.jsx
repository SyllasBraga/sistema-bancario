import React, { useState, useEffect } from "react";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import FormularioPessoa from "../components/FormularioPessoa";
import TabelaPessoa from "../components/TabelaPessoa";
import Paginacao from "../components/Paginacao";
import ModalErro from "../components/ModalErro";
import ModalConfirmacao from "../components/ModalConfirmacao";
import { listarPessoas, criarPessoa, atualizarPessoa, deletarPessoa } from "../services/clients/PessoaClient";
import { getCepInfo } from "../services/clients/CepClient";
import { ValidaModelos } from "../utils/ValidaModelos";

const PessoaPage = () => {
  const [pessoas, setPessoas] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [modalMessage, setModalMessage] = useState("");
  const [editMode, setEditMode] = useState(false);
  const [currentId, setCurrentId] = useState(null);
  const [isConfirmModalOpen, setIsConfirmModalOpen] = useState(false);
  const [pessoaParaExcluir, setPessoaParaExcluir] = useState(null);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [editableFields, setEditableFields] = useState({numero: false, rua: false, cidade: false, estado: false,});

  const { register, handleSubmit, getValues, setValue, reset, formState: { errors, touchedFields } } = useForm({
    resolver: yupResolver(ValidaModelos),
    defaultValues: {
      nome: "",
      cpf: "",
      dataNascimento: "",
      endereco: {
        cep: "",
        rua: "",
        cidade: "",
        estado: "",
        numero: "",
      },
    },
    mode: "onBlur",
  });

  const fetchPessoas = async (page = 0) => {
    const data = await listarPessoas(page);
    setPessoas(data.content || []);
    setCurrentPage(data.page.number);
    setTotalPages(data.page.totalPages);
  };

  const handleSave = async (data) => {

    const processedData = {
      ...data,
      cpf: data.cpf.replace(/\D/g, ""),
      endereco: {
        ...data.endereco,
        cep: data.endereco.cep.replace(/\D/g, ""),
      },
    };

    if(processedData.endereco.cep === "" || processedData.endereco.cep===null){
      delete processedData['endereco']
    }

    try {
      if (editMode) {
        await atualizarPessoa(currentId, processedData);
      } else {
        await criarPessoa(processedData);
      }
      fetchPessoas();
      setEditMode(false);
      setCurrentId(null);
      reset();
    } catch (error) {
      setModalMessage("O CPF informado já está cadastrado!");
      setIsModalOpen(true);
    }
  };

  const handleEdit = (pessoa) => {
    setValue("nome", pessoa.nome);
    setValue("cpf", pessoa.cpf);
    setValue("dataNascimento", pessoa.dataNascimento);

    if (pessoa.endereco) {
      setValue("endereco.cep", pessoa.endereco.cep || "");
      setValue("endereco.rua", pessoa.endereco.rua || "");
      setValue("endereco.cidade", pessoa.endereco.cidade || "");
      setValue("endereco.estado", pessoa.endereco.estado || "");
      setValue("endereco.numero", pessoa.endereco.numero || "");
    }
    setEditMode(true);
    setCurrentId(pessoa.idPessoa);
  };

  const abrirModalConfirmacao = (pessoa) => {
    setEditMode(false);
    reset();
    setPessoaParaExcluir(pessoa);
    setIsConfirmModalOpen(true);
  };

  const fecharModalConfirmacao = () => {
    setPessoaParaExcluir(null);
    setIsConfirmModalOpen(false);
  };

  const confirmarExclusao = async () => {
    if (pessoaParaExcluir) {
      await deletarPessoa(pessoaParaExcluir.idPessoa);
      fetchPessoas();
      fecharModalConfirmacao();
    }
  };

  const fetchAddressByCep = async () => {
    const cep = getValues("endereco.cep");
    const cepFormated = cep.replace(/\D/g, "");
    try {
      const address = await getCepInfo(cepFormated);
      if (address) {
        setValue("endereco.rua", address.rua);
        setValue("endereco.cidade", address.cidade);
        setValue("endereco.estado", address.estado);
        const rua = address.rua === null;
        console.log(rua)
        setEditableFields({ numero: true, rua:rua, cidade: false, estado: false });
      } else {
        setEditableFields({ numero: true, rua: true, cidade: true, estado: true });
      }
    } catch (error) {
      console.error(error);
      setEditableFields({ numero: true, rua: true, cidade: true, estado: true });
    }
  };

  useEffect(() => {
    fetchPessoas();
  }, []);

  return (
    <div className="container mx-auto">
      <h1 className="text-2xl font-bold mb-4">Cadastro de Pessoa</h1>
      <FormularioPessoa
        register={register}
        errors={errors}
        touchedFields={touchedFields}
        fetchAddressByCep={fetchAddressByCep}
        onSubmit={handleSubmit(handleSave)}
        setValue={setValue}
        editableFields={editableFields}
      />
      <TabelaPessoa pessoas={pessoas} onEdit={handleEdit} onDelete={abrirModalConfirmacao} />
      <div className="border-t bg-gray-50 fixed bottom-0 w-[60%] p-4 flex justify-center">
        <Paginacao
          currentPage={currentPage}
          totalPages={totalPages}
          onPageChange={fetchPessoas}
        />
        <ModalErro isOpen={isModalOpen} onRequestClose={() => setIsModalOpen(false)} mensagem={modalMessage} />
        <ModalConfirmacao
          isOpen={isConfirmModalOpen}
          onDeletePessoa={confirmarExclusao}
          onRequestClose={fecharModalConfirmacao}
          pessoaNome={pessoaParaExcluir?.nome}
        />
      </div>
    </div >
  );
};

export default PessoaPage;