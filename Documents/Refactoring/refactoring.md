## Рефакторинг

После проведения рефакторинга были выявлены следующие проблемы в коде:
1. Мёртвый код
2. Дублирование кода
3. Комментарии, оставленные после проверки работоспособности кода
4. Неиспользуемые переменные

Мёртвый код был обнаружен в классе AddController (Commit: https://github.com/ArtemBakun650502/YouTube-Downloader/commit/cce4f0f43af3e95b325c30279d4577ace61d33f0), с установкой нового URL. 
Решение: удалить.

Дублирование кода было обнаружено в классах DownloadController (Commit: https://github.com/ArtemBakun650502/YouTube-Downloader/commit/cce4f0f43af3e95b325c30279d4577ace61d33f0) и DownloadLaterController (Commit: https://github.com/ArtemBakun650502/YouTube-Downloader/commit/cce4f0f43af3e95b325c30279d4577ace61d33f0) в строках с 149 по 187, связанное с практически одинаковой реализацией скачивания.</br>
Решение: создание нового метода downloadVideo в классе DownloadController.

Комментарии, оставленные после проверки работоспособности кода, были обнаружены практически во всех классах. 
Решение: удалить.

Неиспользуемая переменная, а именно String url, была обнаружена в классе DownloadLaterController (Commit: https://github.com/ArtemBakun650502/YouTube-Downloader/commit/cce4f0f43af3e95b325c30279d4577ace61d33f0). Она была необходима для сохранения адреса видео, но её полностью заменила переменная String fileName.
Решение: удалить.
