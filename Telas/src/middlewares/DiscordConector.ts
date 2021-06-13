import { Webhook, MessageBuilder } from 'discord-webhook-node'
import moment from 'moment'
import { Atividade, Aula, Prova, Trabalho } from 'src/@types/DB'
import { Store } from 'vuex'
import { StateInterface } from 'src/store'

export class Discord {

    private hook: Webhook
    private store: Store<StateInterface>

    constructor(link: string, store: Store<StateInterface>) { this.hook = new Webhook(link); this.store = store; moment.locale('pt-br') }

    aula = {

        add: (aula: Aula) => {

            this.hook.send(

                new MessageBuilder()
                .setAuthor(`Aula de ${aula.materia.nome} adicinada!`)
                .addField("Nome", aula.nome)
                .addField("Descrição", aula.descricao)
                .addField("Materia", aula.materia.nome)
                .addField("Turma", this.store.state.turmas.find(t => t.idTurma == aula.idTurma)?.nome as string)
                .addField("Data", moment(aula.inicio).format("DD/MM/YYYY hh:mm A - dddd") )
                .addField("Link", (!aula.link.startsWith('https://')) ? `[${aula.link}](https://${aula.link})` : `[${aula.link}](${aula.link})`)
                .setFooter("Klaza • © Todos os direitos reservados.")
                
            )

        },

        update: (aula: Aula) => {

            this.hook.send(

                new MessageBuilder()
                .setAuthor(`Aula de ${aula.materia.nome} modificada!`)
                .addField("Nome", aula.nome)
                .addField("Descrição", aula.descricao)
                .addField("Materia", aula.materia.nome)
                .addField("Data", moment(aula.inicio).format("DD/MM/YYYY hh:mm A - dddd") )
                .addField("Link", (!aula.link.startsWith('https://')) ? `[${aula.link}](https://${aula.link})` : `[${aula.link}](${aula.link})`)
                .setFooter("Klaza • © Todos os direitos reservados.")
                
            )

        },

        delete: (aula: Aula) => {

            this.hook.send(

                new MessageBuilder()
                .setAuthor(`Aula de ${aula.materia.nome} removida!`)
                .addField("Nome", aula.nome)
                .setFooter("Klaza • © Todos os direitos reservados.")
                
            )

        }

    }

    trabalho = {

        add: (trabalho: Trabalho) => {

            this.hook.send(

                new MessageBuilder()
                .setAuthor(`Trabalho de ${trabalho.materia.nome} adicinado!`)
                .addField("Nome", trabalho.nome)
                .addField("Descrição", trabalho.descricao)
                .addField("Materia", trabalho.materia.nome)
                .addField("Turma", this.store.state.turmas.find(t => t.idTurma == trabalho.idTurma)?.nome as string)
                .addField("Data", moment(trabalho.inicio).format("DD/MM/YYYY hh:mm A - dddd") )
                .setFooter("Klaza • © Todos os direitos reservados.")
                
            )

        },

        update: (trabalho: Trabalho) => {

            this.hook.send(

                new MessageBuilder()
                .setAuthor(`Trabalho de ${trabalho.materia.nome} modificado!`)
                .addField("Nome", trabalho.nome)
                .addField("Descrição", trabalho.descricao)
                .addField("Materia", trabalho.materia.nome)
                .addField("Data", moment(trabalho.inicio).format("DD/MM/YYYY hh:mm A - dddd") )
                .setFooter("Klaza • © Todos os direitos reservados.")
                
            )

        },

        delete: (trabalho: Trabalho) => {

            this.hook.send(

                new MessageBuilder()
                .setAuthor(`Trabalho de ${trabalho.materia.nome} removido!`)
                .addField("Nome", trabalho.nome)
                .setFooter("Klaza • © Todos os direitos reservados.")
                
            )

        }

    }

    prova = {

        add: (prova: Prova) => {

            this.hook.send(

                new MessageBuilder()
                .setAuthor(`Prova de ${prova.materia.nome} adicinada!`)
                .addField("Nome", prova.nome)
                .addField("Descrição", prova.descricao)
                .addField("Materia", prova.materia.nome)
                .addField("Turma", this.store.state.turmas.find(t => t.idTurma == prova.idTurma)?.nome as string)
                .addField("Data", moment(prova.inicio).format("DD/MM/YYYY hh:mm A - dddd") )
                .setFooter("Klaza • © Todos os direitos reservados.")
                
            )

        },

        update: (prova: Prova) => {

            this.hook.send(

                new MessageBuilder()
                .setAuthor(`Prova de ${prova.materia.nome} modificado!`)
                .addField("Nome", prova.nome)
                .addField("Descrição", prova.descricao)
                .addField("Materia", prova.materia.nome)
                .addField("Data", moment(prova.inicio).format("DD/MM/YYYY hh:mm A - dddd") )
                .setFooter("Klaza • © Todos os direitos reservados.")
                
            )

        },

        delete: (prova: Prova) => {

            this.hook.send(

                new MessageBuilder()
                .setAuthor(`Prova de ${prova.materia.nome} removida!`)
                .addField("Nome", prova.nome)
                .setFooter("Klaza • © Todos os direitos reservados.")
                
            )

        }

    }

    atividade = {

        add: (atividade: Atividade) => {

            this.hook.send(

                new MessageBuilder()
                .setAuthor(`Atividade de ${atividade.materia.nome} adicinada!`)
                .addField("Nome", atividade.nome)
                .addField("Descrição", atividade.descricao)
                .addField("Materia", atividade.materia.nome)
                .addField("Turma", this.store.state.turmas.find(t => t.idTurma == atividade.idTurma)?.nome as string)
                .addField("Data", moment(atividade.inicio).format("DD/MM/YYYY hh:mm A - dddd") )
                .setFooter("Klaza • © Todos os direitos reservados.")
                
            )

        },

        update: (atividade: Atividade) => {

            this.hook.send(

                new MessageBuilder()
                .setAuthor(`Atividade de ${atividade.materia.nome} modificada!`)
                .addField("Nome", atividade.nome)
                .addField("Descrição", atividade.descricao)
                .addField("Materia", atividade.materia.nome)
                .addField("Data", moment(atividade.inicio).format("DD/MM/YYYY hh:mm A - dddd") )
                .setFooter("Klaza • © Todos os direitos reservados.")
                
            )

        },

        delete: (atividade: Atividade) => {

            this.hook.send(

                new MessageBuilder()
                .setAuthor(`Atividade de ${atividade.materia.nome} removida!`)
                .addField("Nome", atividade.nome)
                .setFooter("Klaza • © Todos os direitos reservados.")
                
            )

        }

    }

}