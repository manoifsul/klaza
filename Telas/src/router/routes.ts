import { RouteConfig } from 'vue-router';

const routes: RouteConfig[] = [

    { path: '/', redirect: '/login' },
    {
        path: '/login',
        component: () => import('layouts/LoginLayout.vue'),
        children: [
            { path: '', component: () => import('src/pages/Login.vue') }
        ]
    },
    {
        path: '/home',
        component: () => import('layouts/MainLayout.vue'),
        children: [
          { path: '', component: () => import('src/pages/Home.vue') }
        ]
    },
    {
        path: '/notas',
        component: () => import('layouts/MainLayout.vue'),
        children: [
          { path: '', component: () => import('src/pages/Notas.vue') }
        ]
    },
    {
        path: '/trabalho/:idTrabalho/:nTentativa',
        component: () => import('layouts/MainLayout.vue'),
        children: [
          { path: '', component: () => import('src/pages/Trabalho.vue') }
        ]
    },
    {
        path: '/tentativas/trabalho/:idTrabalho/:idAluno/:nTentativa',
        component: () => import('layouts/MainLayout.vue'),
        children: [
          { path: '', component: () => import('src/pages/TrabalhoView.vue') }
        ]
    },
    {
        path: '/prova/:idProva/:nTentativa',
        component: () => import('layouts/MainLayout.vue'),
        children: [
          { path: '', component: () => import('src/pages/Prova.vue') }
        ]
    },
    {
        path: '/tentativas/prova/:idProva/:idAluno/:nTentativa',
        component: () => import('layouts/MainLayout.vue'),
        children: [
          { path: '', component: () => import('src/pages/ProvaView.vue') }
        ]
    },

    // Always leave this as last one,
    // but you can also remove it
    {
        path: '*',
        component: () => import('pages/Error404.vue')
    }
];

export default routes;
