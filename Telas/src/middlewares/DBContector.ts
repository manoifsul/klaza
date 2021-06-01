import { AxiosInstance } from 'axios'
import { Administrador, Aluno, Professor } from '../@types/DB'
import { Store } from 'vuex'
import { StateInterface } from 'src/store'

export class DB {

    private baseUrl = "localhost:3000"
    private axios: AxiosInstance
    private store: Store<StateInterface>

    constructor(axios: AxiosInstance, store: Store<StateInterface>) {

        this.axios = axios
        this.store = store

    }

    dias = {

        get: () => {}

    }

    aluno = {

        get: async (id?: number) => {
           
            if (id) {

                await this.axios({

                    url: `${this.baseUrl}/aluno/${id}`,
                    method: "GET"

                }).then(r => {
                    
                    //ADD ALGUMA MANIPULACAO PARA ALUNO + ADD NO VUEX

                })

            }
            else {

                await this.axios({

                    url: `${this.baseUrl}/alunos`,
                    method: "GET"

                }).then(r => {

                    //ADD ALGUMA MANIPULACAO PARA ALUNO + ADD NO VUEX

                })

            }

        },

        create: async (aluno: Aluno) => {

            await this.axios({

                url: `${this.baseUrl}/aluno`,
                method: "POST",
                data: aluno

            }).then(r => {
                
                //ADD ALGUMA MANIPULACAO PARA ALUNO + ADD NO VUEX

            })

        },

        update: async (id: string, aluno: Aluno) => {

            await this.axios({

                url: `${this.baseUrl}/aluno/${id}`,
                method: "PUT",
                data: aluno

            }).then(r => {
                
                //ADD ALGUMA MANIPULACAO PARA ALUNO + ADD NO VUEX

            })

        },

        delete: async (id: string) => {

            await this.axios({

                url: `${this.baseUrl}/aluno/${id}`,
                method: "DELETE"

            }).then(r => {
                
                //ADD ALGUMA MANIPULACAO PARA ALUNO + ADD NO VUEX

            })

        }

    }

    professor = {

        get: async (id?: number) => {
           
            if (id) {

                await this.axios({

                    url: `${this.baseUrl}/professor/${id}`,
                    method: "GET"

                }).then(r => {
                    
                    //ADD ALGUMA MANIPULACAO PARA PROFESSOR + ADD NO VUEX

                })

            }
            else {

                await this.axios({

                    url: `${this.baseUrl}/professores`,
                    method: "GET"

                }).then(r => {

                    //ADD ALGUMA MANIPULACAO PARA PROFESSOR + ADD NO VUEX

                })

            }

        },

        create: async (professor: Professor) => {

            await this.axios({

                url: `${this.baseUrl}/professor`,
                method: "POST",
                data: professor

            }).then(r => {
                
                //ADD ALGUMA MANIPULACAO PARA PROFESSOR + ADD NO VUEX

            })

        },

        update: async (id: string, professor: Professor) => {

            await this.axios({

                url: `${this.baseUrl}/professor/${id}`,
                method: "PUT",
                data: professor

            }).then(r => {
                
                //ADD ALGUMA MANIPULACAO PARA PROFESSOR + ADD NO VUEX

            })

        },

        delete: async (id: string) => {

            await this.axios({

                url: `${this.baseUrl}/professor/${id}`,
                method: "DELETE"

            }).then(r => {
                
                //ADD ALGUMA MANIPULACAO PARA PROFESSOR + ADD NO VUEX

            })

        }

    }

    administrador = {

        get: async (id?: number) => {
           
            if (id) {

                await this.axios({

                    url: `${this.baseUrl}/administrador/${id}`,
                    method: "GET"

                }).then(r => {
                    
                    //ADD ALGUMA MANIPULACAO PARA ADMINISTRADOR + ADD NO VUEX

                })

            }
            else {

                await this.axios({

                    url: `${this.baseUrl}/administradores`,
                    method: "GET"

                }).then(r => {

                    //ADD ALGUMA MANIPULACAO PARA ADMINISTRADOR + ADD NO VUEX

                })

            }

        },

        create: async (administrador: Administrador) => {

            await this.axios({

                url: `${this.baseUrl}/administrador`,
                method: "POST",
                data: administrador

            }).then(r => {
                
                //ADD ALGUMA MANIPULACAO PARA ADMINISTRADOR + ADD NO VUEX

            })

        },

        update: async (id: string, administrador: Administrador) => {

            await this.axios({

                url: `${this.baseUrl}/administrador/${id}`,
                method: "PUT",
                data: administrador

            }).then(r => {
                
                //ADD ALGUMA MANIPULACAO PARA ADMINISTRADOR + ADD NO VUEX

            })

        },

        delete: async (id: string) => {

            await this.axios({

                url: `${this.baseUrl}/administrador/${id}`,
                method: "DELETE"

            }).then(r => {
                
                //ADD ALGUMA MANIPULACAO PARA ADMINISTRADOR + ADD NO VUEX

            })

        }

    }

    aula = {

        get: async (id?: number) => {
           
            if (id) {

                await this.axios({

                    url: `${this.baseUrl}/aula/${id}`,
                    method: "GET"

                }).then(r => {
                    
                    //ADD ALGUMA MANIPULACAO PARA AULA + ADD NO VUEX

                })

            }
            else {

                await this.axios({

                    url: `${this.baseUrl}/aulas`,
                    method: "GET"

                }).then(r => {

                    //ADD ALGUMA MANIPULACAO PARA AULA + ADD NO VUEX

                })

            }

        },

        create: async (administrador: Administrador) => {

            await this.axios({

                url: `${this.baseUrl}/aula`,
                method: "POST",
                data: administrador

            }).then(r => {
                
                //ADD ALGUMA MANIPULACAO PARA ADMINISTRADOR + ADD NO VUEX

            })

        },

        update: async (id: string, administrador: Administrador) => {

            await this.axios({

                url: `${this.baseUrl}/administrador/${id}`,
                method: "PUT",
                data: administrador

            }).then(r => {
                
                //ADD ALGUMA MANIPULACAO PARA ADMINISTRADOR + ADD NO VUEX

            })

        },

        delete: async (id: string) => {

            await this.axios({

                url: `${this.baseUrl}/administrador/${id}`,
                method: "DELETE"

            }).then(r => {
                
                //ADD ALGUMA MANIPULACAO PARA ADMINISTRADOR + ADD NO VUEX

            })

        }

    }

}